package com.spring.study.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "board")
@Data
public class Board {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    // 제목
    private String title;
    // 내용
    private String content;

    // 사용자
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Board(String title, String content, Users user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
