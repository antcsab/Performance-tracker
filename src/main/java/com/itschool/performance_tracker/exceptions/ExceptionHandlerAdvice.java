package com.itschool.performance_tracker.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    public ExceptionHandlerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> bookNotFoundException(PlayerNotFoundException playerNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", playerNotFoundException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(PlayerDeleteException.class)
    public ResponseEntity<String> customerDeleteException(PlayerDeleteException playerDeleteException) {
        return new ResponseEntity<>(objectToString(Map.of("message", playerDeleteException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(PlayerDuplicateException.class)
    public ResponseEntity<String> playerDuplicateException(PlayerDuplicateException playerDuplicateException) {
        return new ResponseEntity<>(objectToString(Map.of("message", playerDuplicateException.getMessage())), NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(objectToString(errors), BAD_REQUEST);
    }

    private String objectToString(Object response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("Error processing response to string");
            return "Internal error";
        }
    }
}
