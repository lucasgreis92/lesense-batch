package br.com.spintec.logicae.lesensebatch.model;


public class Invoices {

  private long invoiceId;
  private String username;
  private java.sql.Timestamp cycle;
  private java.sql.Timestamp created;
  private String content;
  private double total;
  private double discount;


  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public java.sql.Timestamp getCycle() {
    return cycle;
  }

  public void setCycle(java.sql.Timestamp cycle) {
    this.cycle = cycle;
  }


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }


  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

}
