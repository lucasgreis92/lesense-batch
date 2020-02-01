package br.com.spintec.logicae.lesensebatch.model;


public class Configs {

  private String configId;
  private String username;
  private String deviceSerial;
  private String name;
  private String value;
  private java.sql.Timestamp updated;


  public String getConfigId() {
    return configId;
  }

  public void setConfigId(String configId) {
    this.configId = configId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getDeviceSerial() {
    return deviceSerial;
  }

  public void setDeviceSerial(String deviceSerial) {
    this.deviceSerial = deviceSerial;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public java.sql.Timestamp getUpdated() {
    return updated;
  }

  public void setUpdated(java.sql.Timestamp updated) {
    this.updated = updated;
  }

}
