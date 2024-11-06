package br.com.oak.sistemapagamentoapi.model;

public enum StatusTransacao {
  INICIADA,
  AGUARDANDO_CONFIRMACAO_PAGAMENTO,
  FALHA,
  FINALIZADA
}
