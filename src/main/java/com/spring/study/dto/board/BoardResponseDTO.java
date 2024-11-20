package com.spring.study.dto.board;

import com.spring.study.domain.Board;
import com.spring.study.domain.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BoardResponseDTO {

    private Long id;
    private String title;
    private String content;
    private Users user;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.user = board.getUser();
    }
}
