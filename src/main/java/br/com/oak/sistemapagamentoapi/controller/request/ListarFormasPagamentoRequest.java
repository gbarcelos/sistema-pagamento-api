package br.com.oak.sistemapagamentoapi.controller.request;

import jakarta.validation.constraints.NotNull;

public class ListarFormasPagamentoRequest {

  @NotNull
  private Long usuarioId;

  @NotNull
  private Long restauranteId;

  public ListarFormasPagamentoRequest(Long usuarioId, Long restauranteId) {
    this.usuarioId = usuarioId;
    this.restauranteId = restauranteId;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public Long getRestauranteId() {
    return restauranteId;
  }
}
