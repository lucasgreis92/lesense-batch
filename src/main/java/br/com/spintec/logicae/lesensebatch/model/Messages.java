package br.com.spintec.logicae.lesensebatch.model;


public class Messages {

  private String messageId;
  private String username;
  private String messageFrom;
  private java.sql.Timestamp sent;
  private java.sql.Timestamp ack;
  private String message;
  private String globalId;


  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getMessageFrom() {
    return messageFrom;
  }

  public void setMessageFrom(String messageFrom) {
    this.messageFrom = messageFrom;
  }


  public java.sql.Timestamp getSent() {
    return sent;
  }

  public void setSent(java.sql.Timestamp sent) {
    this.sent = sent;
  }


  public java.sql.Timestamp getAck() {
    return ack;
  }

  public void setAck(java.sql.Timestamp ack) {
    this.ack = ack;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public String getGlobalId() {
    return globalId;
  }

  public void setGlobalId(String globalId) {
    this.globalId = globalId;
  }

}
