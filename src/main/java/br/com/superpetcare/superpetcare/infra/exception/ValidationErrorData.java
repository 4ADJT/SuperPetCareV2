package br.com.superpetcare.superpetcare.infra.exception;

import org.springframework.validation.FieldError;

record ValidationErrorData(
        String field,
        String message
) {
    public ValidationErrorData(FieldError error) {
        this(
                error.getField(),
                error.getDefaultMessage()
        );
    }
}
