package br.com.spintec.logicae.lesensebatch.mapper;

import br.com.spintec.logicae.lesensebatch.dto.SensorsDtoV1;
import br.com.spintec.logicae.lesensebatch.model.Sensors;
import org.springframework.stereotype.Component;

@Component
public class SensorsMapper extends AbstractMapper<Sensors, SensorsDtoV1> {

    @Override
    public Sensors convertToModel(SensorsDtoV1 dto, Sensors model) {
        return null;
    }

    @Override
    public SensorsDtoV1 convertToDto(Sensors model) {
        SensorsDtoV1 dto = new SensorsDtoV1();
        dto.setId(model.getId());
        dto.setSerial(model.getDeviceSerial());
        dto.setModel(model.getModel());
        dto.setTimestamp(model.getCollected());
        dto.setVersion(model.getVersion());
        dto.setValue(model.getValue());
        dto.setSensor(model.getPort());
        dto.setType(model.getType());
        return dto;
    }
}
