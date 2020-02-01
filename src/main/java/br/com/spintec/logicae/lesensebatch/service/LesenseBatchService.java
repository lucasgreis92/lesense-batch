package br.com.spintec.logicae.lesensebatch.service;

import br.com.spintec.logicae.lesensebatch.controller.v1.LesenseBatchController;
import br.com.spintec.logicae.lesensebatch.dto.SensorsDtoV1;
import br.com.spintec.logicae.lesensebatch.mapper.SensorsMapper;
import br.com.spintec.logicae.lesensebatch.model.*;
import br.com.spintec.logicae.lesensebatch.repository.*;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

@Service
public class LesenseBatchService {

    @Autowired
    private LesenseBatchRepository lesenseBatchRepository;

    @Autowired
    private SensorsService sensorsService;

    @Autowired
    private DevicesRepository devicesRepository;

    @Autowired
    private CallbackMarkupService callbackMarkupService;

    @Autowired
    private CallbacksRepository callbacksRepository;

    @Autowired
    private SensorsMapper sensorsMapper;

    @Autowired
    private CallbackService callbackService;

    @Value("${lesense.package.size}")
    private String lesensePackgeSize;

    private Semaphore semaphoreGenerateCallbackMarkup = new Semaphore(1);
    private static boolean ieGenerateCallbackMarkup = false;
    private static boolean ieSendSensors = false;
    private Semaphore semaphoreSendSensors = new Semaphore(1);

    final static Logger log = LoggerFactory.getLogger(LesenseBatchService.class);

    @Async
    public void generateCallbackMarkupStart() {
        try {
            semaphoreGenerateCallbackMarkup.acquire();
            if (ieGenerateCallbackMarkup) {
                return;
            }
            ieGenerateCallbackMarkup = true;
            semaphoreGenerateCallbackMarkup.release();

            log.info("iniciado generateCallbackMarkup");
            generateCallbackMarkup();
            log.info("finalizado generateCallbackMarkup");

        } catch (Exception ex) {
            log.error("erro generateCallbackMarkup",ex);
        } finally {
            try {
                semaphoreGenerateCallbackMarkup.acquire();
                ieGenerateCallbackMarkup = false;
                semaphoreGenerateCallbackMarkup.release();
            } catch (InterruptedException e) {
                log.error("erro generateCallbackMarkup", e);
            }

        }
    }

    public void sendSensorsStart() {
        try {
            semaphoreSendSensors.acquire();
            if (ieSendSensors) {
                return;
            }
            ieSendSensors= true;
            semaphoreSendSensors.release();

            log.info("iniciado sendSensors");
            sendSensors();
            log.info("finalizado sendSensors");

        } catch (Exception ex) {
            log.error("erro sendSensors",ex);
        } finally {
            try {
                semaphoreSendSensors.acquire();
                ieSendSensors = false;
                semaphoreSendSensors.release();
            } catch (Exception e) {
                log.error("erro sendSensors",e);
            }
        }
    }




    public void generateCallbackMarkup() {
        LesenseBatch batch = null;
        Long qtdRowsGenerated = 0L;
        Long qtdRowsSended = 0L;
        String error = null;
        List<CallbackMarkup> inserts = new ArrayList<>();
        try {
            batch = newLesenseBatch();
            List<Sensors> newDataList = sensorsService.findNewSensors();
            log.info("Foram encontrados " + newDataList.size() + " sensores");
            List<Devices> devices = devicesRepository.findAll().stream().filter( d -> {

                return d.getCallbacksId() != null
                        && !d.getCallbacksId().toString().isEmpty()
                        && !d.getCallbacksId().toString().equalsIgnoreCase("[]");
            }).collect(Collectors.toList());
            log.info("Foram encontrados " + devices.size() + " devices vinculados a callbacks");

            List<Callbacks> callbacks = callbacksRepository.findAll();
            log.info("Foram encontrados " + callbacks.size() + " callbacks");
            newDataList.forEach(s -> {
                Optional<Devices> device = devices
                        .stream()
                        .filter(d -> {
                            return d.getDeviceSerial() != null
                                    && s.getDeviceSerial() != null
                                    && d.getDeviceSerial().equalsIgnoreCase(s.getDeviceSerial());
                        }).findFirst();

                if (device.isPresent()) {
                    JSONArray callbacksId = new JSONArray(device.get().getCallbacksId().toString());
                    callbacksId.forEach( cbId -> {
                        CallbackMarkup callbackMarkup = new CallbackMarkup();


                        callbackMarkup.setSensorId(s.getId());
                        callbackMarkup.setExecuted(LocalDateTime.now());
                        Optional<Callbacks> cb = callbacks.stream().filter( cc -> {
                            return cc.getCallbackId().toString().equalsIgnoreCase(cbId.toString());
                        }).findFirst();
                        if (cb.isPresent()) {
                            callbackMarkup.setCallbackId(cb.get().getCallbackId());
                            callbackMarkup.setDone(false);
                            callbackMarkup.setUsername(cb.get().getUsername());
                        } else {
                            log.warn("O device " + device.get().getDeviceSerial() + " tem como destino um callback inexistente");
                            callbackMarkup.setDone(true);
                            callbackMarkup.setCallbackId(null);
                            callbackMarkup.setUsername("SEM_CALLBACK");
                        }
                        inserts.add(callbackMarkup);
                    });
                } else {
                    log.warn("O device " + device.get().getDeviceSerial() + " não está cadastrado");
                    CallbackMarkup callbackMarkup = new CallbackMarkup();
                    callbackMarkup.setCallbackId(null);
                    callbackMarkup.setDone(true);
                    callbackMarkup.setSensorId(s.getId());
                    callbackMarkup.setExecuted(LocalDateTime.now());
                    callbackMarkup.setUsername("SEM_DEVICE");
                    inserts.add(callbackMarkup);
                }
            });

            if (!inserts.isEmpty()) {
                log.info("Inserindo " + inserts.size() + " registros na tabela callback_markup");
                callbackMarkupService.saveAll(inserts);
                log.info("Foram inseridos " + inserts.size() + " registros na tabela callback_markup com sucesso");
            }
            qtdRowsGenerated = Long.valueOf(inserts.size());

        } catch (Exception ex) {
            log.error("erro generateCallbackMarkup", ex);
            error = ex.getMessage();
            for (StackTraceElement trace : ex.getStackTrace()) {
                error += "\n" + ex.getStackTrace()[0].toString();
            }
        } finally {
            finalizarBatch(batch,qtdRowsGenerated,qtdRowsSended,error);
        }


    }

    public LesenseBatch newLesenseBatch() {
        LesenseBatch batch = new LesenseBatch();
        batch.setDtCreate(LocalDateTime.now());
        batch.setQtdRowsSended(0L);
        batch.setQtdRowsGenerated(0L);
        return lesenseBatchRepository.save(batch);
    }

    public LesenseBatch finalizarBatch(LesenseBatch batch, Long qtdRowsGenerated, Long qtdRowsSended,String error) {
        batch.setDtFinished(LocalDateTime.now());
        batch.setQtdRowsGenerated(qtdRowsGenerated);
        batch.setQtdRowsSended(qtdRowsSended);
        batch.setError(error);
        return lesenseBatchRepository.save(batch);
    }

    public void sendSensors() {
        LesenseBatch batch = null;
        Long qtdRowsGenerated = 0L;
        Long qtdRowsSended = 0L;
        String error = null;
        Integer lesensePackgeSizeInt = Integer.valueOf(lesensePackgeSize);
        log.info("Quantidade de registros a serem enviados no pacote " + lesensePackgeSize);
        HashMap<String,List<Future<ResponseEntity<Object>>>> retornoThreads = new HashMap<>();
        try {
            batch = newLesenseBatch();
            List<Callbacks> callbacks = callbacksRepository.findAll();
            log.info("Foram encontrados " + callbacks.size() + " callbacks");
            List<SensorsDtoV1> sendingList = new ArrayList<>();
            List<CallbackMarkup> markupSendingList = new ArrayList<>();
            callbacks.forEach(callback -> {
                if ("off".equalsIgnoreCase(callback.getStatus())) {
                    log.info("O callback " + callback.getDescription() + " está desativado");
                }
                if (!"off".equalsIgnoreCase(callback.getStatus())) {

                    List<CallbackMarkup> markups = callbackMarkupService.findAllByDoneFalseAndCallbackId(callback.getCallbackId());
                    log.info("Foram encontrados " + markups.size() + " markups para o callback " + callback.getDescription() + " que está sendo executado");
                    List<Sensors> sensorsToSend = sensorsService.findToSend();
                    log.info("Foram encontrados " + sensorsToSend.size() + " registros na sensors para o callback " + callback.getDescription() + " que está sendo executado");

                    if (markups.size() == 0 ){
                        callback.setStatus("on");
                        callbackService.save(callback);
                        return;
                    } else if (!"error".equalsIgnoreCase(callback.getStatus())) {
                        callback.setStatus("running");
                        callbackService.save(callback);
                    }
                    log.info("O callback " + callback.getDescription() + " está sendo executado");
                    markups.forEach( m -> {
                        Optional<Sensors> sensors = sensorsToSend
                                .stream().filter(s -> {
                                    return m.getSensorId().toString().equalsIgnoreCase(s.getId().toString());
                                }).findFirst();
                        if (sensors.isPresent()) {
                            SensorsDtoV1 dto = sensorsMapper.convertToDto(sensors.get());
                            sendingList.add(dto);
                            markupSendingList.add(m);
                        } else {
                            log.warn("Não foi encontrado o sensor " + m.getSensorId().toString());
                        }
                        if (lesensePackgeSizeInt <= sendingList.size()) {
                            if (retornoThreads.containsKey(callback.getCallbackId().toString())) {
                                retornoThreads.get(callback.getCallbackId().toString())
                                        .add(sensorsService.send(callback, new ArrayList<>(sendingList),new ArrayList<>(markupSendingList)));
                            } else {
                                List<Future<ResponseEntity<Object>>> lista = new ArrayList<>();
                                lista.add( sensorsService.send(callback, new ArrayList<>(sendingList),new ArrayList<>(markupSendingList)));
                                retornoThreads.put(callback.getCallbackId().toString(),lista);
                            }
                            sendingList.clear();
                            markupSendingList.clear();
                        }

                    });
                    if (!sendingList.isEmpty()) {
                        if (retornoThreads.containsKey(callback.getCallbackId().toString())) {
                            retornoThreads.get(callback.getCallbackId().toString())
                                    .add(sensorsService.send(callback, new ArrayList<>(sendingList),new ArrayList<>(markupSendingList)));
                        } else {
                            List<Future<ResponseEntity<Object>>> lista = new ArrayList<>();
                            lista.add( sensorsService.send(callback, new ArrayList<>(sendingList),new ArrayList<>(markupSendingList)));
                            retornoThreads.put(callback.getCallbackId().toString(),lista);
                        }
                        sendingList.clear();
                        markupSendingList.clear();
                    }

                }
            });
            log.info("Aguardando a finalização da threads de envio ");
            while(retornoThreads.keySet()
                    .stream()
                    .filter( callBackId -> {
                        return retornoThreads.get(callBackId)
                                .stream().filter( res -> {
                                    return !res.isDone();
                                }).findFirst().isPresent();
                    } ).findFirst().isPresent()) {

                Thread.currentThread().sleep(5000);
            }
            log.info("Threads de envio finalizadas ");
            retornoThreads.keySet().forEach( callBackId -> {
                Optional<Future<ResponseEntity<Object>>> future = retornoThreads.get(callBackId)
                        .stream()
                        .filter( res -> {
                            try {
                                return !HttpStatus.OK.equals(res.get().getStatusCode());
                            } catch (InterruptedException e) {
                                return false;
                            } catch (ExecutionException e) {
                                return false;
                            }
                        }).findFirst();

                if (future.isPresent()) {
                    Optional<Callbacks> call = callbacks.stream().filter( c -> {
                        return c.getCallbackId().toString().equalsIgnoreCase(callBackId);
                    }).findFirst();
                    if (call.isPresent()) {
                        log.error("Ocorreram erros na execução do callback " + call.get().getDescription());
                        call.get().setStatus("error");
                    }else {
                        log.info("O callback " + call.get().getDescription() + " foi executado com sucesso");
                        call.get().setStatus("on");
                    }
                    callbackService.save(call.get());
                }
            });

        } catch (Exception ex) {
            log.error("erro sendSensors", ex);
            error = ex.getMessage();
            for (StackTraceElement trace : ex.getStackTrace()) {
                error += "\n" + ex.getStackTrace()[0].toString();
            }
        } finally {
            finalizarBatch(batch, qtdRowsGenerated, qtdRowsSended, error);
        }
    }


}