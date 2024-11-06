package br.com.oak.sistemapagamentoapi.gateway;

import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Transacao;
import br.com.oak.sistemapagamentoapi.service.Resultado;
import java.math.BigDecimal;

public interface GatewayPagamento {

  boolean aceitaFormaDePagamento(Pagamento pagamento);

  BigDecimal custoOperacao(Pagamento pagamento);

  Resultado<Exception, Transacao> processa(Pagamento pagamento);
}
