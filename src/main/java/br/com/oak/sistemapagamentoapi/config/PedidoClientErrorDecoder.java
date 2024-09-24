package br.com.oak.sistemapagamentoapi.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class PedidoClientErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    response.status();
    return null;
  }
}
