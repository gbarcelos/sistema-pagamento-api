package br.com.oak.sistemapagamentoapi.service;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.model.jpa.Usuario;

public interface RegraAntiFraude {
  boolean permiteFormaDePagamento(FormaPagamento formaPagamento, Usuario usuario);
}
