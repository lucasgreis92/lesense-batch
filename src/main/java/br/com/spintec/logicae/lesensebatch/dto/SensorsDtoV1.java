package br.com.spintec.logicae.lesensebatch.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

public class SensorsDtoV1 {

    private UUID id;
   // private String username;
    private String serial;
    private LocalDateTime timestamp;//collected;
   // private LocalDateTime created;
    private String version;
    private String type;
    private Double value;
    private Long sensor;//port;
  //  private String tags;
    private String model;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getSensor() {
        return sensor;
    }

    public void setSensor(Long sensor) {
        this.sensor = sensor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
