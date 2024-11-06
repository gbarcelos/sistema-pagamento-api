package br.com.oak.sistemapagamentoapi.http;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.CreditCardNumber;

public class DadosCartaoSeyaRequest {
  @CreditCardNumber
  private String num_cartao;
  @Min(100)
  @Max(999)
  private int codigo_seguranca;

  public DadosCartaoSeyaRequest(@CreditCardNumber String num_cartao,
      @Min(100) @Max(999) int codigo_seguranca) {
    super();
    this.num_cartao = num_cartao;
    this.codigo_seguranca = codigo_seguranca;
  }

  public DadosCartaoSeyaRequest(DadosCartao dadosCartao) {
    this.num_cartao = dadosCartao.getNumero();
    this.codigo_seguranca = dadosCartao.getCodigoSeguranca();
  }

  public String getNum_cartao() {
    return num_cartao;
  }

  public int getCodigo_seguranca() {
    return codigo_seguranca;
  }

  @Override
  public String toString() {
    return "DadosCartaoSeyaRequest [num_cartao=" + num_cartao
        + ", codigo_seguranca=" + codigo_seguranca + "]";
  }
}
