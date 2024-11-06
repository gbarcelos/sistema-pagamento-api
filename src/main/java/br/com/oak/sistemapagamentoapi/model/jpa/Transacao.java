package br.com.oak.sistemapagamentoapi.model.jpa;

import br.com.oak.sistemapagamentoapi.gateway.GatewayPagamento;
import br.com.oak.sistemapagamentoapi.model.FacilitadorJackson;
import br.com.oak.sistemapagamentoapi.model.StatusTransacao;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Map;
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

  private String informacaoAdicional;

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

  public void setInfoAdicional(Map<String, Object> infoAdicional) {
    this.informacaoAdicional = FacilitadorJackson.serializa(infoAdicional);
  }

  public static Transacao concluida(GatewayPagamento gateway) {
    Transacao transacao = new Transacao(StatusTransacao.FINALIZADA);
    transacao.informacaoAdicional = FacilitadorJackson
        .serializa(Map.of("gateway", gateway.toString()));
    return transacao;
  }
}
