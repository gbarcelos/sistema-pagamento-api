package br.com.oak.sistemapagamentoapi.controller.response;

import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;

public class PagamentoResponse {

  private final String codigo;

  public PagamentoResponse(Pagamento pagamento) {
    this.codigo = pagamento.getCodigo();
  }

  public String getCodigo() {
    return codigo;
  }
}
