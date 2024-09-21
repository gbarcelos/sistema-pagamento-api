package br.com.oak.sistemapagamentoapi.controller.response;

import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;

public class PagamentoResponse {

  private final Long id;

  public PagamentoResponse(Pagamento pagamento) {
    this.id = pagamento.getId();
  }

  public Long getId() {
    return id;
  }
}
