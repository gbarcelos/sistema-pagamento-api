package br.com.oak.sistemapagamentoapi.service;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoRequest;
import br.com.oak.sistemapagamentoapi.http.PedidoClient;
import br.com.oak.sistemapagamentoapi.model.Pedido;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Transacao;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IniciaPagamento {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private PedidoClient pedidoClient; //1

  @Autowired
  private ExecutaTransacao executaTransacao; //1

  public Pagamento executa(Long pedidoId, PagamentoRequest pagamentoRequest) {
    //1 - Pedido
    Pedido pedido = pedidoClient.obterPedidoPorId(pedidoId);

    // 2 Pagameno + excuta
    return executaTransacao.executa(() -> {
      Pagamento pagamentoSalvo = pagamentoRequest.toModel(pedido, entityManager); //1 - toModel
      entityManager.persist(pagamentoSalvo);
      return pagamentoSalvo;
    });
  }
}
