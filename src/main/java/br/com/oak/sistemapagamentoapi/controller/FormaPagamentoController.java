package br.com.oak.sistemapagamentoapi.controller;

import br.com.oak.sistemapagamentoapi.controller.request.ListarFormasPagamentoRequest;
import br.com.oak.sistemapagamentoapi.controller.response.FormaPagamentoResponse;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Restaurante;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

  @GetMapping
  public ResponseEntity<Set<FormaPagamentoResponse>> listarFormasPagamento(@RequestBody @Valid ListarFormasPagamentoRequest request) {

    Usuario usuario = entityManager.find(Usuario.class, request.getUsuarioId());
    Restaurante restaurante = entityManager.find(Restaurante.class, request.getRestauranteId());

    Set<FormaPagamento> formasDePagamentoAceitas = usuario.filtrarFormasDePagamentoAceita(restaurante);

    return ResponseEntity.ok(formasDePagamentoAceitas.stream().map(FormaPagamentoResponse::new).collect(
        Collectors.toSet()));
  }

}
