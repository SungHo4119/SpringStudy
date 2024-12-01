package com.spring.study.useCase.service.util.jwt;

import com.spring.study.domain.user.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j // 로깅을 위한 어노테이션
@Component // 스프링 빈으로 등록
@RequiredArgsConstructor // final 필드를 파라미터로 받는 생성자를 생성
public class JwtUtil {

    // JWT 토큰 생성을 위한 상수
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    private static final String BEAREER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L; // 1시간

    @Value("${jwt.secret.key}") // 불변 객체를 생성하기 위한 어노테이션
    private String secretKey; // JWT 암호화를 위한 키
    private Key key; // 키 객체

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 알고리즘

    @PostConstruct // 객체 생성 후 초기화를 위한 어노테이션
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey); // Base64 디코딩
        key = Keys.hmacShaKeyFor(bytes); // 키 생성
    }

    // HttpServletRequest : HTTP 요청 정보를 담고 있는 객체
    // 토큰 가져오기
    public String resolveToeken(HttpServletRequest request) {
        // 헤더에서 토큰 가져오기
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        // 토큰이 존재하고, Bearer로 시작하면 토큰 반환
        if (bearerToken != null && bearerToken.startsWith(BEAREER_PREFIX)) {
            return bearerToken.substring(7); // 토큰 반환
        }
        return null;
    }

    // 토큰 생성
    public String createToken(String userName, UserRole role) {
        Date date = new Date(); // 현재 시간
        return BEAREER_PREFIX +
                Jwts.builder()
                        .setSubject(userName) // 토큰 제목
                        .claim(AUTHORIZATION_KEY, role) // 클레임 설정
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 만료 시간 설정
                        .signWith(key, signatureAlgorithm) // 키와 알고리즘 설정
                        .compact(); // 토큰 생성
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.error("JWT 토큰이 만료되었습니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰입니다.");
        }
        return false;
    }

    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
