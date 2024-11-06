package br.com.oak.sistemapagamentoapi.service;

import java.util.Arrays;

public class Resultado<E extends Exception, S> {

  private E erro;
  private S sucesso;

  private Resultado() {
  }

  public boolean temErro() {
    return erro != null;
  }

  public E getException() {
    return erro;
  }

  public String getStackTrace() {
    return Arrays.toString(erro.getStackTrace());
  }

  public S get() {
    return sucesso;
  }

  public static <T> Resultado<Exception, T> sucesso(T objeto) {
    Resultado<Exception, T> resultado = new Resultado<Exception, T>();
    resultado.sucesso = objeto;
    return resultado;
  }

  public static <E extends Exception, T> Resultado<E, T> erro(E exception) {
    Resultado<E, T> resultado = new Resultado<E, T>();
    resultado.erro = exception;
    return resultado;
  }

}
