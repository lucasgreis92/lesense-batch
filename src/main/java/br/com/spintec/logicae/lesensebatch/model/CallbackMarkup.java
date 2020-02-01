package br.com.spintec.logicae.lesensebatch.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "callback_markup")
public class CallbackMarkup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Column(name = "sensor_id", columnDefinition = "BINARY(16)")
  private UUID sensorId;
  @Column(name = "callback_id",columnDefinition = "BINARY(16)")
  private UUID callbackId;
  private String username;
  private LocalDateTime executed;
  private Boolean done;

  public UUID getSensorId() {
    return sensorId;
  }

  public void setSensorId(UUID sensorId) {
    this.sensorId = sensorId;
  }

  public UUID getCallbackId() {
    return callbackId;
  }

  public void setCallbackId(UUID callbackId) {
    this.callbackId = callbackId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getExecuted() {
    return executed;
  }

  public void setExecuted(LocalDateTime executed) {
    this.executed = executed;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }
}
