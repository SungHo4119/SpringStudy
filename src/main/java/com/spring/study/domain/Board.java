package com.spring.study.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "boards")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok 기본 생성자 추가 JPA 사용하기위해 기본 생성자 추가
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 제목
    private String title;
    // 내용
    private String content;

    // 사용자
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @CreatedDate
    private LocalDateTime create_at;
    @LastModifiedDate
    private LocalDateTime update_at;

    @Builder
    public Board(String title, String content, Users user, LocalDateTime create_at, LocalDateTime update_at) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.create_at = create_at;
        this.update_at = update_at;
    }
}
