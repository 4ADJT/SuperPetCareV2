package br.com.superpetcare.superpetcare.infra.exception;

public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }
    public ValidatorException(String message, Throwable cause) {
        super(message,cause);
    }
}
