package br.com.oak.sistemapagamentoapi.http;

import br.com.oak.sistemapagamentoapi.model.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("pedidos-api")
public interface PedidoClient {
  @RequestMapping(method = RequestMethod.GET, value = "/pedidos/{id}")
  Pedido obterPedidoPorId(@PathVariable Long id);
}
