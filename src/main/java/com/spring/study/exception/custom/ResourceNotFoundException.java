package com.spring.study.exception.custom;


// 예외 클래스 정의
public class ResourceNotFoundException extends RuntimeException {

    // 생성자에서 예외 메시지를 받음
    public ResourceNotFoundException(String message) {
        super(message);  // 부모 클래스인 RuntimeException에 메시지 전달
    }
}
