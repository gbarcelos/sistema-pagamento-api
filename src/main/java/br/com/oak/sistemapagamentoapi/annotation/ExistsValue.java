package br.com.oak.sistemapagamentoapi.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import br.com.oak.sistemapagamentoapi.validator.ExistsValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExistsValueValidator.class})
public @interface ExistsValue {

  String message() default "{br.com.oak.sistemapagamentoapi.annotation.existsValue}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String fieldName() default "id" ;

  Class<?> domainClass();
}
