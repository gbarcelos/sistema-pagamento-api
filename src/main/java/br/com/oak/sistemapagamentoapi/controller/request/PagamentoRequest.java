package br.com.oak.sistemapagamentoapi.controller.request;

import static br.com.oak.sistemapagamentoapi.model.StatusTransacao.INICIADA;

import br.com.oak.sistemapagamentoapi.annotation.ExistsValue;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.Pedido;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PagamentoRequest {

  @NotNull
  private FormaPagamento formaPagamento;

  @NotNull
  @ExistsValue(domainClass = Usuario.class)
  private Long usuarioId;

  @NotNull
  @ExistsValue(domainClass = Restaurante.class)
  private Long restauranteId;

  @NotBlank
  private String numeroCartao;

  @NotBlank
  private String codigoSeguranca;

  public PagamentoRequest(@NotNull FormaPagamento formaPagamento, @NotNull Long usuarioId,
      @NotNull Long restauranteId, @NotBlank String numeroCartao, @NotBlank String codigoSeguranca) {
    this.formaPagamento = formaPagamento;
    this.usuarioId = usuarioId;
    this.restauranteId = restauranteId;
    this.numeroCartao = numeroCartao;
    this.codigoSeguranca = codigoSeguranca;
  }

  public Pagamento toModel(Pedido pedido, EntityManager entityManager) {
    //Parte 1 - Salvar inicio transação
    Usuario cliente = entityManager.find(Usuario.class,
        usuarioId); //1 - Usuario

    Restaurante restaurante = entityManager.find(Restaurante.class,
        restauranteId); //1 - Restaurante
    return new Pagamento(pedido.getId(),
        pedido.getValorTotal(),
        cliente,
        restaurante,
        INICIADA,
        formaPagamento);
  }

  public boolean isOffline() {
    return !formaPagamento.online;
  }

  public FormaPagamento getFormaPagamento() {
    return formaPagamento;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public Long getRestauranteId() {
    return restauranteId;
  }
}
