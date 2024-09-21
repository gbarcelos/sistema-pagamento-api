package br.com.oak.sistemapagamentoapi.validator;

import br.com.oak.sistemapagamentoapi.annotation.ExistsValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import org.springframework.util.Assert;

public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Object> {

  private String domainAttribute;
  private Class<?> clazz;
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void initialize(ExistsValue params) {
    domainAttribute = params.fieldName();
    clazz = params.domainClass();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    if (value == null){
      return true;
    }

    Query query = entityManager.createQuery(
        "select 1 from " + clazz.getName() + " where "
            + domainAttribute + "=:value");
    query.setParameter("value", value);

    List<?> list = query.getResultList();

    Assert.state(list.size() <= 1,
        "Nenhum registro encontrado de um " + clazz + " com o atributo " + domainAttribute
            + " = " + value);

    return !list.isEmpty();
  }
}
