package com.app.dto;

import java.math.BigDecimal;

public class ClientDTO {

  private String code;
  private Boolean male;
  private Integer type;
  private String company;
  private Boolean encrypt;
  private BigDecimal totalBalance;

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

  public BigDecimal getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(BigDecimal totalBalance) {
    this.totalBalance = totalBalance;
  }
}
