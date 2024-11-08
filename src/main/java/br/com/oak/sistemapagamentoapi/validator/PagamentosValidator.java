package br.com.oak.sistemapagamentoapi.validator;

import br.com.oak.sistemapagamentoapi.controller.request.PagamentoRequest;
import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import br.com.oak.sistemapagamentoapi.service.AvaliaFormasDePagamento;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PagamentosValidator implements Validator {

  @Autowired
  private AvaliaFormasDePagamento avaliaFormasDePagamento; //1

  @Override
  public boolean supports(Class<?> clazz) {
    return PagamentoRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    //1
    new RestringeFormaPagamentoOfflineValidator().validate(target, errors);

    if (errors.hasErrors()) {
      return;
    }
    PagamentoRequest request = (PagamentoRequest) target;
    FormaPagamento formaPagamento = request.getFormaPagamento();

    //1
    final Set<FormaPagamento> formasDePagamentoAceitas = avaliaFormasDePagamento
        .filtrarFormasDePagamentoAceita(request.getUsuarioId(), request.getRestauranteId());

    //1
    if (!formasDePagamentoAceitas.contains(formaPagamento)) {
      errors.rejectValue("formaPagamento", null,
          "A combinacao entre usuario, restaurante e forma de pagamento nao e valida: "
              + formaPagamento);
    }
  }
}
