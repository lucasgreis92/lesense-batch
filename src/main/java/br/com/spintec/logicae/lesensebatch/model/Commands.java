package br.com.spintec.logicae.lesensebatch.model;


public class Commands {

  private String commandId;
  private String username;
  private String deviceSerial;
  private String command;
  private String value;
  private String response;
  private java.sql.Timestamp created;
  private java.sql.Timestamp done;


  public String getCommandId() {
    return commandId;
  }

  public void setCommandId(String commandId) {
    this.commandId = commandId;
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


  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }


  public java.sql.Timestamp getDone() {
    return done;
  }

  public void setDone(java.sql.Timestamp done) {
    this.done = done;
  }

}
