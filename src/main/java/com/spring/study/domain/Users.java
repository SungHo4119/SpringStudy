package com.spring.study.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {
    // Pk
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    // 사용자 이름
    @Column(name = "user_name")
    private String userName;
    // 사용자 비밀번호
    private String password;
}