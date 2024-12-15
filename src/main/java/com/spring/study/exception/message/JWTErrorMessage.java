package com.spring.study.exception.message;

public class JWTErrorMessage {

    public static final String JWT_INVALID_SIGNATURE = "잘못된 JWT 서명입니다.";
    public static final String JWT_EXPIRED = "JWT 토큰이 만료되었습니다.";
    public static final String JWT_UNSUPPORTED = "지원되지 않는 JWT 토큰입니다.";
    public static final String JWT_CLAIMS_EMPTY = "JWT claims가 비어있습니다.";
    public static final String JWT_UNKNOWN_ERROR = "JWT 처리 중 알 수 없는 오류가 발생했습니다.";

}
