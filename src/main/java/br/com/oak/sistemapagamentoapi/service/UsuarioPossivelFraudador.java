package br.com.oak.sistemapagamentoapi.service;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UsuarioPossivelFraudador implements RegraAntiFraude {

  @Override
  public boolean permiteFormaDePagamento(@NotNull FormaPagamento formaPagamento,
      @NotNull @Valid Usuario usuario) {

    if (!formaPagamento.online) {
      return true;
    }

    return !usuario.getPossivelFraudador();
  }
}
