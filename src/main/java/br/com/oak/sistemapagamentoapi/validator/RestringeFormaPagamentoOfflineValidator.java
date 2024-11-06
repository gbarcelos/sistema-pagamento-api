package br.com.oak.sistemapagamentoapi.validator;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestringeFormaPagamentoOfflineValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return PagamentoRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    PagamentoRequest request = (PagamentoRequest) target;

    //1
    if (request.isOffline()) {
      errors.rejectValue("formaPagamento", null,
          "Apenas forma de pagamento online aceita");
    }
  }
}
