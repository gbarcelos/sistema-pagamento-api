package br.com.oak.sistemapagamentoapi.model.jpa;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pagamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Positive
  private BigDecimal valor;

  @NotNull
  @ManyToOne
  private Usuario usuario;

  @NotNull
  @ManyToOne
  private final Restaurante restaurante;

  @Enumerated(EnumType.STRING)
  private Status status;

  @NotNull
  @Enumerated(EnumType.STRING)
  private FormaPagamento formaPagamento;

  private LocalDateTime dataHoraCriacao = LocalDateTime.now();

  public Pagamento(BigDecimal valor,
      Usuario usuario,
      Restaurante restaurante,
      Status status,
      FormaPagamento formaPagamento) {
    this.valor = valor;
    this.usuario = usuario;
    this.restaurante = restaurante;
    this.status = status;
    this.formaPagamento = formaPagamento;
  }

  public Long getId() {
    return id;
  }

}
