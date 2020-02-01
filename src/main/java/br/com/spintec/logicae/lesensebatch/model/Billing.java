package br.com.spintec.logicae.lesensebatch.model;


public class Billing {

  private String billingId;
  private String username;
  private String contractId;
  private java.sql.Timestamp initDate;
  private double value;


  public String getBillingId() {
    return billingId;
  }

  public void setBillingId(String billingId) {
    this.billingId = billingId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }


  public java.sql.Timestamp getInitDate() {
    return initDate;
  }

  public void setInitDate(java.sql.Timestamp initDate) {
    this.initDate = initDate;
  }


  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

}
