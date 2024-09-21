package br.com.oak.sistemapagamentoapi.controller.request;

import static br.com.oak.sistemapagamentoapi.model.Status.AGUARDANDO_CONFIRMACAO_PAGAMENTO;

import br.com.oak.sistemapagamentoapi.annotation.ExistsValue;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.Pedido;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.validation.constraints.NotNull;

public class PagamentoOfflineRequest {

  @NotNull
  private Long pedidoId;

  @NotNull
  //@EnumNamePattern(regexp = "MAQUINA|DINHEIRO")
  private FormaPagamento formaPagamento;

  @NotNull
  @ExistsValue(domainClass = Usuario.class)
  private Long usuarioId;

  @NotNull
  @ExistsValue(domainClass = Restaurante.class)
  private Long restauranteId;

  public PagamentoOfflineRequest(FormaPagamento formaPagamento, Long usuarioId, Long restauranteId,
      Long pedidoId) {
    this.formaPagamento = formaPagamento;
    this.usuarioId = usuarioId;
    this.restauranteId = restauranteId;
    this.pedidoId = pedidoId;
  }

  public Long getPedidoId() {
    return pedidoId;
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

  public Pagamento toModel(Usuario usuario, Restaurante restaurante, Pedido pedido) {
    return new Pagamento(pedido.getValorTotal(),
        usuario,
        restaurante,
        AGUARDANDO_CONFIRMACAO_PAGAMENTO,
        formaPagamento);
  }
}
