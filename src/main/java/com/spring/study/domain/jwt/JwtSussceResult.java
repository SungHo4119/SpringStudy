package com.spring.study.domain.jwt;

import lombok.Builder;

public class JwtSussceResult {

    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtSussceResult(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
