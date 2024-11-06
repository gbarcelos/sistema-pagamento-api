package br.com.oak.sistemapagamentoapi.service;

import br.com.oak.sistemapagamentoapi.gateway.GatewayPagamento;
import br.com.oak.sistemapagamentoapi.model.StatusTransacao;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Transacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegracaoGatewayPagamento {

  @Autowired
  private Set<GatewayPagamento> gateways;

  public List<Transacao> executa(Pagamento pagamento) {

    List<GatewayPagamento> gatewayFiltrados = gateways.stream()
        .filter(gatewayPagamento -> gatewayPagamento.aceitaFormaDePagamento(pagamento))
        .sorted((gateway1, gateway2) -> gateway1.custoOperacao(pagamento)
            .compareTo(gateway2.custoOperacao(pagamento)))
        .toList();

    List<Transacao> transacoes = new ArrayList<>();
    for (GatewayPagamento gatewayPagamento : gatewayFiltrados) {
      Resultado<Exception, Transacao> resultado = gatewayPagamento.processa(pagamento);

      if (resultado.temErro()) {
        Transacao transacao = new Transacao(StatusTransacao.FALHA);
        transacao.setInfoAdicional(
            Map.of("gateway", gatewayPagamento, "exception", resultado.getStackTrace()));
        transacoes.add(transacao);

      } else {
        transacoes.add(resultado.get());
        break;
      }
    }
    return transacoes;
  }
}
