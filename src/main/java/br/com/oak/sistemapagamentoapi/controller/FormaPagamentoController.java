package br.com.oak.sistemapagamentoapi.controller;

import br.com.oak.sistemapagamentoapi.controller.request.ListarFormasPagamentoRequest;
import br.com.oak.sistemapagamentoapi.controller.response.FormaPagamentoResponse;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import br.com.oak.sistemapagamentoapi.service.RegraAntiFraude;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private Collection<RegraAntiFraude> regrasAntiFraude; //1

  @GetMapping
  //1 - FormaPagamentoResponse
  public ResponseEntity<Set<FormaPagamentoResponse>> listarFormasPagamento(
      //1 - ListarFormasPagamentoRequest
      @RequestBody @Valid ListarFormasPagamentoRequest request) {

    //1 - Usuario
    Usuario usuario = entityManager.find(Usuario.class, request.getUsuarioId());

    //1 - Restaurante
    Restaurante restaurante = entityManager.find(Restaurante.class, request.getRestauranteId());

    //1 - filtrarFormasDePagamentoAceita
    Set<FormaPagamento> formasDePagamentoAceitas = usuario.filtrarFormasDePagamentoAceita(
        regrasAntiFraude, restaurante);

    //1 - map
    return ResponseEntity.ok(formasDePagamentoAceitas.stream().map(FormaPagamentoResponse::new).collect(
        Collectors.toSet()));
  }

}
