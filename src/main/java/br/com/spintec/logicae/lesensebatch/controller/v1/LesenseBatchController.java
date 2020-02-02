package br.com.spintec.logicae.lesensebatch.controller.v1;

import br.com.spintec.logicae.lesensebatch.service.LesenseBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component
public class LesenseBatchController {

    @Autowired
    private LesenseBatchService lesenseBatchService;
    private Semaphore semaphoreGenerateCallbackMarkup = new Semaphore(1);
    private static boolean ieGenerateCallbackMarkup = false;
    private static boolean ieSendSensors = false;
    private Semaphore semaphoreSendSensors = new Semaphore(1);

    final static Logger log = LoggerFactory.getLogger(LesenseBatchController.class);

    @Scheduled(cron = "*/10 * * ? * *")
    public void generateCallbackMarkup() {
        lesenseBatchService.generateCallbackMarkupStart();
    }

    @Scheduled(cron = "*/30 * * ? * *")
    public void sendSensors() {
        lesenseBatchService.sendSensorsStart();
    }

}
