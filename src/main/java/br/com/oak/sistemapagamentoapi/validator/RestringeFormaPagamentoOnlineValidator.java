package br.com.oak.sistemapagamentoapi.validator;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoOfflineRequest;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
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

    FormaPagamento formaPagamento = request.getFormaPagamento();
    //1
    if (formaPagamento.online) {
      errors.rejectValue("formaPagamento", null,
          "Apenas forma de pagamento offline aceita: " + formaPagamento);
    }
  }
}
