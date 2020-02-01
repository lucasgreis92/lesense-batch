package br.com.spintec.logicae.lesensebatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "callbacks")
public class Callbacks {

  @Id
  @Column(name = "callback_id",columnDefinition = "BINARY(16)")
  private UUID callbackId;
  private String username;
  private String filter;
  private String url;
  private String description;
  private LocalDateTime created;
  private String status;


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


  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
