package br.com.oak.sistemapagamentoapi.model.jpa;

import static br.com.oak.sistemapagamentoapi.model.StatusTransacao.FINALIZADA;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.StatusTransacao;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.util.Assert;

@Entity
public class Pagamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String codigo;

  @NotNull
  private Long pedidoId;

  @NotNull
  @Positive
  private BigDecimal valor;

  @NotNull
  @Valid
  @ManyToOne
  private Usuario cliente;

  @NotNull
  @Valid
  @ManyToOne
  private Restaurante restaurante;

  @NotNull
  @Enumerated(EnumType.STRING)
  private FormaPagamento formaPagamento;

  @ElementCollection
  private Set<Transacao> transacoes = new HashSet<>();

  private LocalDateTime dataHoraPagamento = LocalDateTime.now();

  @Deprecated
  public Pagamento() {
  }

  public Pagamento(@NotNull Long pedidoId, @NotNull
  @Positive BigDecimal valor,
      @NotNull @Valid Usuario cliente,
      @NotNull @Valid Restaurante restaurante,
      @NotNull StatusTransacao statusTransacao,
      @NotNull FormaPagamento formaPagamento) {
    this.pedidoId = pedidoId;
    this.valor = valor;
    this.cliente = cliente;
    this.restaurante = restaurante;
    this.formaPagamento = formaPagamento;
    this.transacoes.add(new Transacao(statusTransacao));
    this.codigo = UUID.randomUUID().toString();
  }

  public String getCodigo() {
    return codigo;
  }

  public boolean isConcluido() {
    return this.transacoes.stream().anyMatch(
        transacao -> transacao.possuiStatus(FINALIZADA));
  }

  public void finaliza() {
    Assert.state(!isConcluido(),
        "Não é possível finalizar uma compra que já foi finalizada");
    this.transacoes.add(new Transacao(FINALIZADA));
  }
}
