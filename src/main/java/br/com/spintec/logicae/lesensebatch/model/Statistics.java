package br.com.spintec.logicae.lesensebatch.model;


public class Statistics {

  private String value;
  private String username;
  private java.sql.Timestamp created;
  private String key;


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

}
