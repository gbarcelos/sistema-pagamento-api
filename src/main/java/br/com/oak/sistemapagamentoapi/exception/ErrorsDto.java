package br.com.oak.sistemapagamentoapi.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDto {

  private List<String> globalErrorMessages = new ArrayList<>();
  private List<FieldErrorDto> fieldErrors = new ArrayList<>();

  public void addError(String message) {
    globalErrorMessages.add(message);
  }

  public void addFieldError(String field, String message) {
    FieldErrorDto fieldError = new FieldErrorDto(field, message);
    fieldErrors.add(fieldError);
  }

  public List<String> getGlobalErrorMessages() {
    return globalErrorMessages;
  }

  public List<FieldErrorDto> getErrors() {
    return fieldErrors;
  }

  public int getNumberOfErrors() {
    return this.globalErrorMessages.size() + this.fieldErrors.size();
  }
}
