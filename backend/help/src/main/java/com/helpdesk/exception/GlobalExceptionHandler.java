package com.helpdesk.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> test(ConstraintViolationException violation) {
        return ResponseEntity.badRequest().body(new ModelTratamentoViolation("cpf invalido"));
    }

    private class ModelTratamentoViolation {
        String erro;

        public ModelTratamentoViolation(String erro) {
            this.erro = erro;
        }
    }
}
