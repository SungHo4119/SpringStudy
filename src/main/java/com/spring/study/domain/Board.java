package com.spring.study.domain;

import com.spring.study.dto.board.BoardResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "boards")
@Data
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDateTime create_at;
    @LastModifiedDate
    private LocalDateTime update_at;

    public Board(String title, String content, Users user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }


    public BoardResponseDTO toBoardResponseDTO() {
        return new BoardResponseDTO(this);
    }

}
