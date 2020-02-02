package br.com.spintec.logicae.lesensebatch.controller.v1;

import br.com.spintec.logicae.lesensebatch.service.LesenseBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component
public class SendSensorsController {

    @Autowired
    private LesenseBatchService lesenseBatchService;

    @Scheduled(cron = "*/30 * * ? * *")
    public void sendSensors() {
        lesenseBatchService.sendSensorsStart();
    }

}
