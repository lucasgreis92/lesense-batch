package br.com.spintec.logicae.lesensebatch.controller.v1;

import br.com.spintec.logicae.lesensebatch.service.LesenseBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SensorTestController {

    @Autowired
    private LesenseBatchService lesenseBatchService;

    @Scheduled(cron = "*/30 * * ? * *")
    public void generateSensorTest() {
        lesenseBatchService.generateSensorTest();
    }
}
