package com.fiap.mottuguard.infra.error;

import com.fiap.mottuguard.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.TransientObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<RestErrorMessage> resourceNotFoundHandler(ResourceNotFoundException exception){
        RestErrorMessage treatedResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatedResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMessage> runTimeExceptionHandler(RuntimeException exception){
        RestErrorMessage treatedResponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(treatedResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<RestValidationErrorMessage> ConstraintViolationExceptionHandler(ConstraintViolationException exception){
        List<String> details = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            details.add(violation.getMessageTemplate());
        }
        RestValidationErrorMessage treatedResponse = new RestValidationErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, details);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(treatedResponse);
    }

    @ExceptionHandler(TransientObjectException.class)
    private ResponseEntity<RestErrorMessage> TransientObjectExceptionHandler(TransientObjectException exception){
        RestErrorMessage treatedResponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Sinto muito, um erro interno aconteceu, tente novamente");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(treatedResponse);
    }


}

