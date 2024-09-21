package br.com.oak.sistemapagamentoapi.model;

import java.math.BigDecimal;

public class Pedido {

  private Long id;
  private BigDecimal valorTotal;

  public Pedido(){
  }

  public Pedido(Long id, BigDecimal valorTotal) {
    this.id = id;
    this.valorTotal = valorTotal;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getValorTotal() {
    return valorTotal;
  }
}
