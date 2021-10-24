package com.app.model;

import java.math.BigDecimal;

public class Client {

  private Long id;
  private String code;
  private Boolean male;
  private Integer type;
  private String location;
  private String company;
  private Boolean encrypt;
  private BigDecimal totalBalance;

  public BigDecimal getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(BigDecimal totalBalance) {
    this.totalBalance = totalBalance;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Boolean getMale() {
    return male;
  }

  public void setMale(Boolean male) {
    this.male = male;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public Boolean getEncrypt() {
    return encrypt;
  }

  public void setEncrypt(Boolean encrypt) {
    this.encrypt = encrypt;
  }
}
