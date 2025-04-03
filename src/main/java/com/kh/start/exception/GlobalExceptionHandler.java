package com.kh.start.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private ResponseEntity<?> makeResponseEntity(RuntimeException e, HttpStatus status){
    Map<String, String> error = new HashMap();
    error.put("error-message", e.getMessage());
    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(InvalidUserREquestException.class)
  public ResponseEntity<?> handleInvalidUserREquestException(InvalidUserREquestException e){
    // Map<String, String> error = new HashMap();
    // error.put("error-message", e.getMessage());
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    return makeResponseEntity(e, HttpStatus.UNAUTHORIZED);
  }
  
  @ExceptionHandler(MemberIdDuplicateException.class)
  public ResponseEntity<?> handleMemberIdDuplicateException(MemberIdDuplicateException e) {
    // Map<String, String> errors = new HashMap();
    // errors.put("error-message", e.getMessage());
    // return ResponseEntity.badRequest().body(errors);
    return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomAuthenticationException.class)
  public ResponseEntity<?> handleCustomAuthenticationException(CustomAuthenticationException e) {
    // Map<String, String> errors = new HashMap();
    // errors.put("error-message", e.getMessage());
    // return ResponseEntity.badRequest().body(errors);
    return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleArgumentsNotValid(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap();
    // List list = e.getBindingResult().getFieldErrors();
    e.getBindingResult()
     .getFieldErrors()
     .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }


}
