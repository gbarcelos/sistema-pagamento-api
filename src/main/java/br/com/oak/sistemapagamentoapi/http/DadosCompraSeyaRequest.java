package br.com.oak.sistemapagamentoapi.http;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.CreditCardNumber;

public class DadosCompraSeyaRequest {

  @CreditCardNumber
  private String num_cartao;
  @Min(100)
  @Max(999)
  private int codigo_seguranca;
  @Positive
  private BigDecimal valor_compra;

  public DadosCompraSeyaRequest(@CreditCardNumber String num_cartao,
      @Min(100) @Max(999) int codigo_seguranca,
      @Positive BigDecimal valor_compra) {
    super();
    this.num_cartao = num_cartao;
    this.codigo_seguranca = codigo_seguranca;
    this.valor_compra = valor_compra;
  }

  public DadosCompraSeyaRequest(DadosCartao dadosCartao, BigDecimal valor) {
    this.num_cartao = dadosCartao.getNumero();
    this.codigo_seguranca = dadosCartao.getCodigoSeguranca();
    this.valor_compra = valor;
  }

  public DadosCompraSeyaRequest() {
    // TODO Auto-generated constructor stub
  }

  public int getCodigo_seguranca() {
    return codigo_seguranca;
  }

  public String getNum_cartao() {
    return num_cartao;
  }

  public BigDecimal getValor_compra() {
    return valor_compra;
  }

  @Override
  public String toString() {
    return "DadosCompraSeyaRequest [num_cartao=" + num_cartao
        + ", codigo_seguranca=" + codigo_seguranca + ", valor_compra="
        + valor_compra + "]";
  }
}
