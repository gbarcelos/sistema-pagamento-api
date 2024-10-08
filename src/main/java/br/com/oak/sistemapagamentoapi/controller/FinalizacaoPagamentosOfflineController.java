package br.com.oak.sistemapagamentoapi.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import br.com.oak.sistemapagamentoapi.repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FinalizacaoPagamentosOfflineController {

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @Transactional
  @PostMapping(value = "/pagamentos/offline/{codigoPagamento}/finalizar")
  public ResponseEntity<?> finalizarPagamentoOffline(
      @PathVariable(value = "codigoPagamento") String codigoPagamento) {
    Optional<Pagamento> pagamentoOptional = pagamentoRepository.findByCodigo(codigoPagamento);
    if (pagamentoOptional.isEmpty()) {
      throw new ResponseStatusException(NOT_FOUND, "Pagamento não encontrado");
    }

    Pagamento pagamento = pagamentoOptional.get();

    if (pagamento.isConcluido()) {
      throw new ResponseStatusException(BAD_REQUEST, "Pagamento já concluído");
    }

    pagamento.finaliza();

    return ResponseEntity.ok().build();
  }
}
