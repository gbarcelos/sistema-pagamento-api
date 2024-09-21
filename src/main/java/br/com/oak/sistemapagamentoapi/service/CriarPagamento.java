package br.com.oak.sistemapagamentoapi.service;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoOfflineRequest;
import br.com.oak.sistemapagamentoapi.model.Pedido;
import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CriarPagamento {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public Pagamento executa(Pedido pedido, PagamentoOfflineRequest pagamentoOfflineRequest) {

    //1 - Usuario
    Usuario usuario = entityManager.find(Usuario.class, pagamentoOfflineRequest.getUsuarioId());

    //1 - Restaurante
    Restaurante restaurante = entityManager.find(Restaurante.class,
        pagamentoOfflineRequest.getRestauranteId());

    //1 - toModel
    Pagamento pagamento = pagamentoOfflineRequest.toModel(usuario, restaurante, pedido);
    entityManager.persist(pagamento);
    return pagamento;
  }
}
