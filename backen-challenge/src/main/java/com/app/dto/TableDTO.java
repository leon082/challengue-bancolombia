package com.app.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class TableDTO {
  private String name;
  private List<ClientDTO> listClients = new ArrayList<>();
  private BigDecimal finalRange;
  private BigDecimal initialRange;
  private String status;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ClientDTO> getListClients() {
    return listClients;
  }

  public void setListClients(List<ClientDTO> listClients) {
    this.listClients = listClients;
  }

  public BigDecimal getFinalRange() {
    return finalRange;
  }

  public void setFinalRange(BigDecimal finalRange) {
    this.finalRange = finalRange;
  }

  public BigDecimal getInitialRange() {
    return initialRange;
  }

  public void setInitialRange(BigDecimal initialRange) {
    this.initialRange = initialRange;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
