package br.com.oak.sistemapagamentoapi.controller;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoRequest;
import br.com.oak.sistemapagamentoapi.controller.response.PagamentoResponse;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Transacao;
import br.com.oak.sistemapagamentoapi.service.ExecutaTransacao;
import br.com.oak.sistemapagamentoapi.service.IniciaPagamento;
import br.com.oak.sistemapagamentoapi.service.IntegracaoGatewayPagamento;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentosController {

  @Autowired
  private IniciaPagamento iniciaPagamento;//1

  @Autowired
  private IntegracaoGatewayPagamento integracaoGatewayPagamento;//1

  @Autowired
  private ExecutaTransacao executaTransacao;

  @PostMapping(value = "/pagamentos/{pedidoId}")
  //1 - PagamentoResponse
  public ResponseEntity<PagamentoResponse> realizarPagamento(
      @PathVariable Long pedidoId,
      @RequestBody @Valid PagamentoRequest request) { //1 - PagamentoRequest

    //1 - iniciaPagamento
    Pagamento pagamento = iniciaPagamento.executa(pedidoId, request);

    //1
    List<Transacao> transacoes = integracaoGatewayPagamento.executa(pagamento);

    //1
    executaTransacao.executa(() -> {
      pagamento.adicionaTransacoes(transacoes);
      return null;
    });

    //1 - PagamentoResponse
    return ResponseEntity.ok(new PagamentoResponse(pagamento));
  }
}
