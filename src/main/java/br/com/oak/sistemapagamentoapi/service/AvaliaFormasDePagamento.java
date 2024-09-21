package br.com.oak.sistemapagamentoapi.service;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliaFormasDePagamento {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private Collection<RegraAntiFraude> regrasAntiFraude;

  public Set<FormaPagamento> filtrarFormasDePagamentoAceita(Long usuarioId,
      Long restauranteId) {

    //1 - Usuario
    Usuario usuario = entityManager.find(Usuario.class, usuarioId);

    //1 - Restaurante
    Restaurante restaurante = entityManager.find(Restaurante.class, restauranteId);

    return usuario.filtrarFormasDePagamentoAceita(regrasAntiFraude, restaurante);
  }
}
