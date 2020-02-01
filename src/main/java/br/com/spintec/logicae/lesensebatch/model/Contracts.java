package br.com.spintec.logicae.lesensebatch.model;


public class Contracts {

  private String contractId;
  private String username;
  private String description;
  private java.sql.Timestamp created;
  private java.sql.Timestamp expires;
  private String disabled;
  private String identifier;


  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }


  public java.sql.Timestamp getExpires() {
    return expires;
  }

  public void setExpires(java.sql.Timestamp expires) {
    this.expires = expires;
  }


  public String getDisabled() {
    return disabled;
  }

  public void setDisabled(String disabled) {
    this.disabled = disabled;
  }


  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

}
