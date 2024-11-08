package br.com.oak.sistemapagamentoapi.controller;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoOfflineRequest;
import br.com.oak.sistemapagamentoapi.controller.response.PagamentoResponse;
import br.com.oak.sistemapagamentoapi.http.PedidoClient;
import br.com.oak.sistemapagamentoapi.model.Pedido;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.service.CriarPagamentoOffline;
import br.com.oak.sistemapagamentoapi.validator.PagamentosOfflineValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentosOfflineController {

  @Autowired
  private PagamentosOfflineValidator pagamentosOfflineValidator; //1 - pagamentosOfflineValidator

  @Autowired
  private PedidoClient pedidoClient;

  @Autowired
  private CriarPagamentoOffline criarPagamentoOffline;

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(pagamentosOfflineValidator);
  }

  @PostMapping(value = "/pagamentos/offline/{pedidoId}")
  //1 - PagamentoResponse
  public ResponseEntity<PagamentoResponse> realizarPagamentoOffline(
      @PathVariable Long pedidoId, @RequestBody @Valid PagamentoOfflineRequest request) { //1 - PagamentoOfflineRequest

    //1 - Pedido
    Pedido pedido = pedidoClient.obterPedidoPorId(pedidoId);

    //1 - criarPagamento
    Pagamento pagamento = criarPagamentoOffline.executa(pedido, request);

    //1 - PagamentoResponse
    return ResponseEntity.ok(new PagamentoResponse(pagamento));
  }
}
