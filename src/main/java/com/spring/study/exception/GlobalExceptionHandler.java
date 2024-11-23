package com.spring.study.exception;


import com.spring.study.exception.custom.AlreadyExistsException;
import com.spring.study.exception.custom.ResourceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

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

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handlerMethodValidationExceptions(HandlerMethodValidationException ex) {
        List<String> errors = ex.getAllValidationResults().stream()
                .flatMap(
                        validationResult -> validationResult.getResolvableErrors().stream()
                                .map(resolvableError -> resolvableError.getDefaultMessage().toString())
                )
                .collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", errors);
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
    public ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyExistsException ex) {
        List<String> message = List.of(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("CONFLICT", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // 모든 예외를 처리하는 메소드
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        Map<String, String> errorDetails = new HashMap<>();

        // 예외의 메시지를 그대로 반환 (필요에 따라 메시지를 추가적으로 처리 가능)
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("cause", ex.getCause() != null ? ex.getCause().toString() : "N/A");

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
