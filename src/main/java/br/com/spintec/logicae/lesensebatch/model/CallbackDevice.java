package br.com.spintec.logicae.lesensebatch.model;


public class CallbackDevice {

  private String callbackId;
  private long callbackDeviceId;
  private String deviceSerial;
  private java.sql.Timestamp dtCreated;


  public String getCallbackId() {
    return callbackId;
  }

  public void setCallbackId(String callbackId) {
    this.callbackId = callbackId;
  }


  public long getCallbackDeviceId() {
    return callbackDeviceId;
  }

  public void setCallbackDeviceId(long callbackDeviceId) {
    this.callbackDeviceId = callbackDeviceId;
  }


  public String getDeviceSerial() {
    return deviceSerial;
  }

  public void setDeviceSerial(String deviceSerial) {
    this.deviceSerial = deviceSerial;
  }


  public java.sql.Timestamp getDtCreated() {
    return dtCreated;
  }

  public void setDtCreated(java.sql.Timestamp dtCreated) {
    this.dtCreated = dtCreated;
  }

}
