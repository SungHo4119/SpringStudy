package com.spring.study.exception;


import com.spring.study.exception.custom.AlreadyExistsException;
import com.spring.study.exception.custom.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

// @ControllerAdvice 어노테이션을 사용하여 전역 예외 처리를 위한 클래스를 정의
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler 어노테이션을 사용하여 MethodArgumentNotValidException 예외를 처리
    // validation 실패 시 발생하는 예외를 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", errors);
        // 400 상태 코드와 함께 오류 정보 반환
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    // ResourceNotFoundException 처리
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        List<String> message = List.of(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public  ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyExistsException ex) {
        List<String> message = List.of(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("CONFLICT", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
