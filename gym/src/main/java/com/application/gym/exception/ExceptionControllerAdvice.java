package com.application.gym.exception;

import org.apache.coyote.BadRequestException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ErrorMessage exceptionHandler(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(ex.getMessage());
        return errorMessage;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
    public ResponseEntity<ErrorMessage> methodArgumentAndConstraintViolationException(Exception ex) {
        String errorMessage = "";
        if(ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException mve = (MethodArgumentNotValidException) ex;
            errorMessage = mve.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(","));
        } else {
            ConstraintViolationException cve = (ConstraintViolationException) ex;
            errorMessage = cve.getConstraintName();
        }
        ErrorMessage error = new ErrorMessage();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(errorMessage);
        return new ResponseEntity<>(error,HttpStatus.OK);
    }
}
