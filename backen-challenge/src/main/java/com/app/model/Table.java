package com.app.model;

import com.app.utils.Criteria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
  private String name;
  private Map<Criteria, Object> criteria;
  private List<Client> listClients = new ArrayList<>();
  private BigDecimal finalRange;
  private BigDecimal initialRange;


  public Table() {
    this.criteria = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map getCriteria() {
    return criteria;
  }


  public List<Client> getListClients() {
    return listClients;
  }

  public void setListClients(List<Client> listClients) {
    this.listClients = listClients;
  }

  public void setCriteria(Map<Criteria, Object> criteria) {
    this.criteria = criteria;
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


}
