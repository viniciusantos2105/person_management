package com.person.management.exceptions.commons;

import com.person.management.exceptions.ApiError;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ValidationError extends ApiError {

    private Map<String, String> errors;
    public static final String DEFAULT_MESSAGE = "Erro de validação";

    public ValidationError(LocalDateTime timestamp, Integer status, String error, String path, Map<String, String> errors) {
        super(timestamp, status, error, path);
        this.errors = errors;
    }

    public static ValidationError createValidationError(LocalDateTime timestamp, Integer status, String error, String path, Map<String, String> errors) {
        return new ValidationError(timestamp, status, error, path, errors);
    }
}
