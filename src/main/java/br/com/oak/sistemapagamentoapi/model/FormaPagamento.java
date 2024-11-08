package br.com.oak.sistemapagamentoapi.model;

import java.util.Arrays;

public enum FormaPagamento {

  VISA(true, "cartao visa"), MASTER(true, "cartao master"),
  ELO(true, "cartao elo"), MAQUINA(false, "maquina para passar cartao"),
  DINHEIRO(false, "dinheiro para pagar o pedidod");

  public final boolean online;
  private String descricao;

  FormaPagamento(boolean online, String descricao) {
    this.online = online;
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public boolean estaEntreAsFormasAceitas(FormaPagamento... formasDePagamentoAceitas) {
    return Arrays.asList(formasDePagamentoAceitas).contains(this);
  }
}
