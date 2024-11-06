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
 * opera na argentina,
 * aceita todas as bandeiras
 * custo que varia entre fixo e variável.
 *  Para compras de até 100 reais, o custo é fixo de 4.00.
 *  Para operações com valor maior que 100, o custo vira de 6% do valor total.
 */
@Service
public class GatewayTango implements GatewayPagamento {

  @Override
  public boolean aceitaFormaDePagamento(Pagamento pagamento) {
    FormaPagamento formaPagamento = pagamento.getFormaPagamento();
    return formaPagamento.estaEntreAsFormasAceitas(MASTER, VISA, ELO);
  }

  @Override
  public BigDecimal custoOperacao(Pagamento pagamento) {
    BigDecimal valor = pagamento.getValor();
    BigDecimal bigDecimal = new BigDecimal("100.0");
    if (valor.compareTo(bigDecimal) <= 0){
      return new BigDecimal("4.0");
    }
    return null;//6% do valor total
  }

  @Override
  public Resultado<Exception, Transacao> processa(Pagamento pagamento) {
    return null;
  }
}
