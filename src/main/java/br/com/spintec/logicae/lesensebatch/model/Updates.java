package br.com.spintec.logicae.lesensebatch.model;


public class Updates {

  private String id;
  private String updateId;
  private long part;
  private String crc;
  private String data;
  private java.sql.Timestamp created;
  private String disabled;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getUpdateId() {
    return updateId;
  }

  public void setUpdateId(String updateId) {
    this.updateId = updateId;
  }


  public long getPart() {
    return part;
  }

  public void setPart(long part) {
    this.part = part;
  }


  public String getCrc() {
    return crc;
  }

  public void setCrc(String crc) {
    this.crc = crc;
  }


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }


  public String getDisabled() {
    return disabled;
  }

  public void setDisabled(String disabled) {
    this.disabled = disabled;
  }

}
