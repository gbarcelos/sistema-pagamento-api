package br.com.oak.sistemapagamentoapi.http;

import br.com.oak.sistemapagamentoapi.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${enderecos-externos.gateways.base-url}", name = "gateways")
public interface RequestsGateways {

  @PostMapping("/seya/verifica")
  int seyaVerifica(DadosCartaoSeyaRequest request);

  @PostMapping(value = "/seya/processa/{codigo}")
  public void seyaProcessa(@PathVariable("codigo") Integer codigo,
      DadosCompraSeyaRequest request);

  @PostMapping(value = "/saori/processa")
  public void saoriProcessa(DadosCompraGenerico request);

  @PostMapping(value = "/tango/processa")
  public void tangoProcessa(DadosCompraGenerico request);

}
