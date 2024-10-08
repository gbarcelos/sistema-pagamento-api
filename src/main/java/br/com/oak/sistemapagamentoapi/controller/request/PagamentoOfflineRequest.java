package br.com.oak.sistemapagamentoapi.controller.request;

import static br.com.oak.sistemapagamentoapi.model.StatusTransacao.AGUARDANDO_CONFIRMACAO_PAGAMENTO;

import br.com.oak.sistemapagamentoapi.annotation.ExistsValue;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.Pedido;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.validation.constraints.NotNull;

public class PagamentoOfflineRequest {

  @NotNull
  //@EnumNamePattern(regexp = "MAQUINA|DINHEIRO")
  private FormaPagamento formaPagamento;

  @NotNull
  @ExistsValue(domainClass = Usuario.class)
  private Long usuarioId;

  @NotNull
  @ExistsValue(domainClass = Restaurante.class)
  private Long restauranteId;

  public PagamentoOfflineRequest(FormaPagamento formaPagamento, Long usuarioId,
      Long restauranteId) {
    this.formaPagamento = formaPagamento;
    this.usuarioId = usuarioId;
    this.restauranteId = restauranteId;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public Long getRestauranteId() {
    return restauranteId;
  }

  public FormaPagamento getFormaPagamento() {
    return formaPagamento;
  }

  public boolean isOnline() {
    return formaPagamento.online;
  }

  public Pagamento toModel(Usuario cliente, Restaurante restaurante, Pedido pedido) {
    return new Pagamento(pedido.getId(),
        pedido.getValorTotal(),
        cliente,
        restaurante,
        AGUARDANDO_CONFIRMACAO_PAGAMENTO,
        formaPagamento);
  }
}
