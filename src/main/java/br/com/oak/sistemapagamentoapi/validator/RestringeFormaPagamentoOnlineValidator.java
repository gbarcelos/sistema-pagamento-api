package br.com.oak.sistemapagamentoapi.validator;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoOfflineRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestringeFormaPagamentoOnlineValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return PagamentoOfflineRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    PagamentoOfflineRequest request = (PagamentoOfflineRequest) target;

    //1
    if (request.isOnline()) {
      errors.rejectValue("formaPagamento", null,
          "Apenas forma de pagamento offline aceita");
    }
  }
}
