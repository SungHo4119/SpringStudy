package com.spring.study.exception;


import com.spring.study.exception.custom.AlreadyExistsException;
import com.spring.study.exception.custom.ResourceNotFoundException;
import com.spring.study.exception.message.JWTErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
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
    // validation 실패 시 발생하는 예외를 처리 ( Body )
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", errors);
        // 400 상태 코드와 함께 오류 정보 반환
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // HandlerMethodValidationException 예외를 처리
    // validation 실패 시 발생하는 예외를 처리 Parameter에 대한 예외 처리
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

    // AlreadyExistsException 처리
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyExistsException ex) {
        List<String> message = List.of(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("CONFLICT", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // JWT 예외 처리
    @ExceptionHandler({SecurityException.class,
            MalformedJwtException.class,
            ExpiredJwtException.class,
            UnsupportedJwtException.class,
            IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleJwtException(Exception ex) {

        // 각 예외에 따라 다른 메시지를 설정할 수 있습니다.
        String errorMessage;
        if (ex instanceof SecurityException || ex instanceof MalformedJwtException) {
            errorMessage = JWTErrorMessage.JWT_INVALID_SIGNATURE;
        } else if (ex instanceof ExpiredJwtException) {
            errorMessage = JWTErrorMessage.JWT_EXPIRED;
        } else if (ex instanceof UnsupportedJwtException) {
            errorMessage = JWTErrorMessage.JWT_UNSUPPORTED;
        } else if (ex instanceof IllegalArgumentException) {
            errorMessage = JWTErrorMessage.JWT_CLAIMS_EMPTY;
        } else {
            errorMessage = JWTErrorMessage.JWT_UNKNOWN_ERROR;
        }

        // ErrorResponse 객체 생성
        ErrorResponse errorResponse = new ErrorResponse("UNAUTHORIZED", List.of(errorMessage));

        // HTTP 상태 코드: 401 (UNAUTHORIZED) 반환
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
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
