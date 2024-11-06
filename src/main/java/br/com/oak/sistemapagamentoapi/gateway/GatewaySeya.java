package br.com.oak.sistemapagamentoapi.gateway;

import static br.com.oak.sistemapagamentoapi.model.FormaPagamento.ELO;
import static br.com.oak.sistemapagamentoapi.model.FormaPagamento.MASTER;
import static br.com.oak.sistemapagamentoapi.model.FormaPagamento.VISA;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Transacao;
import br.com.oak.sistemapagamentoapi.service.Resultado;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 * opera no brasil, aceita todas bandeiras, tem custo fixo de 6.00.
 */
@Service
public class GatewaySeya implements GatewayPagamento {

  @Override
  public boolean aceitaFormaDePagamento(Pagamento pagamento) {
    FormaPagamento formaPagamento = pagamento.getFormaPagamento();
    return formaPagamento.estaEntreAsFormasAceitas(MASTER, VISA, ELO);
  }

  @Override
  public BigDecimal custoOperacao(Pagamento pagamento) {
    return new BigDecimal("6.00");
  }

  @Override
  public Resultado<Exception, Transacao> processa(Pagamento pagamento) {
    return null;
  }
}
