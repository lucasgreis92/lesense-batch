package br.com.spintec.logicae.lesensebatch.service;

import br.com.spintec.logicae.lesensebatch.dto.PacoteSensorsDtoV1;
import br.com.spintec.logicae.lesensebatch.dto.SensorsDtoV1;
import br.com.spintec.logicae.lesensebatch.model.CallbackMarkup;
import br.com.spintec.logicae.lesensebatch.model.Callbacks;
import br.com.spintec.logicae.lesensebatch.model.Devices;
import br.com.spintec.logicae.lesensebatch.model.Sensors;
import br.com.spintec.logicae.lesensebatch.repository.SensorsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

@Service
public class SensorsService {

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private CallbackMarkupService callbackMarkupService;

    private RestTemplate restTemplate = new RestTemplate();

    final static Logger log = LoggerFactory.getLogger(SensorsService.class);

    @Async
    public Future<ResponseEntity<Object>> send(Callbacks callbacks, List<SensorsDtoV1> pacote,  List<CallbackMarkup> markupSendingList) {
        try {
            log.info("Enviando pacote com " + pacote.size() + " registros para o callback " + callbacks.getDescription());
            ResponseEntity<Object> res = restTemplate.postForEntity(callbacks.getUrl(), pacote, Object.class);
            if (HttpStatus.OK.equals(res.getStatusCode())) {
                markupSendingList.forEach(m -> {
                    m.setDone(true);
                });
                callbackMarkupService.saveAll(markupSendingList);
            } else {
                log.error("Ocorreu um erro ao enviar um pacote com " + pacote.size() + " registros para o callback " + callbacks.getDescription());
            }
            return new AsyncResult<>(res);
        } catch(Exception ex) {
            log.error(ex.getMessage());
            log.error("Ocorreu um erro ao enviar um pacote com " + pacote.size() + " registros para o callback " + callbacks.getDescription());
            return new AsyncResult(ResponseEntity.status(500).build());
        }
    }

    public List<Sensors> findNewSensors() {
        return sensorsRepository.findNewSensors();
    }

    public List<Sensors> findToSend() {
        return sensorsRepository.findToSend();
    }

    public Optional<Sensors> findById(UUID sensorId) {
        return sensorsRepository.findById(sensorId);
    }

    public List<Sensors>  selectOldValue(){
        return sensorsRepository.selectOldValue();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll(List<Sensors> sensors) {
        sensorsRepository.deleteAll(sensors);
    }
}
