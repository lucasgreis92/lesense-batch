package br.com.spintec.logicae.lesensebatch.model;


public class Tags {

  private String username;
  private String deviceSerial;
  private String tags;
  private java.sql.Timestamp updated;


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


  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }


  public java.sql.Timestamp getUpdated() {
    return updated;
  }

  public void setUpdated(java.sql.Timestamp updated) {
    this.updated = updated;
  }

}
