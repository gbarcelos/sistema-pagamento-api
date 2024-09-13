package br.com.oak.sistemapagamentoapi.controller.response;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;

public class FormaPagamentoResponse {

  private final String id;
  private final String descricao;

  public FormaPagamentoResponse(FormaPagamento formaPagamento){
    this.id = formaPagamento.name();
    this.descricao = formaPagamento.getDescricao();
  }

  public String getId() {
    return id;
  }

  public String getDescricao() {
    return descricao;
  }
}
