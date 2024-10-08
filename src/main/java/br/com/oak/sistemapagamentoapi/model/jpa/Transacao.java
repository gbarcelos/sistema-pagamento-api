package br.com.oak.sistemapagamentoapi.model.jpa;

import br.com.oak.sistemapagamentoapi.model.StatusTransacao;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
public class Transacao {

  @Enumerated(EnumType.STRING)
  private StatusTransacao statusTransacao;

  @NotBlank
  private String codigo;

  @NotNull
  @PastOrPresent
  private LocalDateTime dataHoraTransacao;

  @Deprecated
  public Transacao() {
  }

  public Transacao(StatusTransacao statusTransacao) {
    this.statusTransacao = statusTransacao;
    this.codigo = UUID.randomUUID().toString();
    this.dataHoraTransacao = LocalDateTime.now();
  }


  public boolean possuiStatus(StatusTransacao statusTransacao) {
    return this.statusTransacao.equals(statusTransacao);
  }
}
