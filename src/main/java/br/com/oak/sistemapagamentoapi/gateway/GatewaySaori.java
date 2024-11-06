package br.com.oak.sistemapagamentoapi.gateway;

import static br.com.oak.sistemapagamentoapi.model.FormaPagamento.MASTER;
import static br.com.oak.sistemapagamentoapi.model.FormaPagamento.VISA;

import br.com.oak.sistemapagamentoapi.http.DadosCompraGenerico;
import br.com.oak.sistemapagamentoapi.http.RequestsGateways;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Transacao;
import br.com.oak.sistemapagamentoapi.service.Resultado;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewaySaori implements GatewayPagamento {

  @Autowired
  private RequestsGateways requestsGateways;

  @Override
  public boolean aceitaFormaDePagamento(Pagamento pagamento) {
    FormaPagamento formaPagamento = pagamento.getFormaPagamento();
    return formaPagamento.estaEntreAsFormasAceitas(MASTER, VISA);
  }

  @Override
  public BigDecimal custoOperacao(Pagamento pagamento) {
    return pagamento.getValor().multiply(new BigDecimal("0.01"));
  }

  @Override
  public Resultado<Exception, Transacao> processa(Pagamento pagamento) {
    DadosCompraGenerico  request = new DadosCompraGenerico(pagamento);
    requestsGateways.saoriProcessa(request);
    return Resultado.sucesso(Transacao.concluida(this));
  }
}
