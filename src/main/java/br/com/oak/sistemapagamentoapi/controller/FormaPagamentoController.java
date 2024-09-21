package br.com.oak.sistemapagamentoapi.controller;

import br.com.oak.sistemapagamentoapi.controller.request.ListarFormasPagamentoRequest;
import br.com.oak.sistemapagamentoapi.controller.response.FormaPagamentoResponse;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.service.AvaliaFormasDePagamento;
import jakarta.validation.Valid;
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

  @Autowired
  private AvaliaFormasDePagamento avaliaFormasDePagamento; //1

  @GetMapping
  //1 - FormaPagamentoResponse
  public ResponseEntity<Set<FormaPagamentoResponse>> listarFormasPagamento(
      //1 - ListarFormasPagamentoRequest
      @RequestBody @Valid ListarFormasPagamentoRequest request) {

    //1 - avaliaFormasDePagamento
    Set<FormaPagamento> formasDePagamentoAceitas = avaliaFormasDePagamento
        .filtrarFormasDePagamentoAceita(request.getUsuarioId(), request.getRestauranteId());

    //1 - map
    return ResponseEntity.ok(formasDePagamentoAceitas.stream().map(FormaPagamentoResponse::new).collect(
        Collectors.toSet()));
  }
}
