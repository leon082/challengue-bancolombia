package com.app.utils;

public enum Criteria {

  TC("type"), UG("location"), RF("final_range"), RI("initial_range");
  private String campo;

  Criteria(String campo) {
    this.campo = campo;
  }

  public String getCampo() {
    return campo;
  }

  public void setCampo(String campo) {
    this.campo = campo;
  }
}
