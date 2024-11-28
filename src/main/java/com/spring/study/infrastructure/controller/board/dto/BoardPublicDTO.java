package com.spring.study.infrastructure.controller.board.dto;

import com.spring.study.useCase.service.board.dto.IBoardPublicDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BoardPublicDTO implements IBoardPublicDTO {

    private Long id;
    private String title;
    private String content;
    private Long user_id;
    private LocalDateTime create_at;
    private LocalDateTime update_at;

    @Builder
    public BoardPublicDTO(
            Long id,
            String title,
            String content,
            Long user_id,
            LocalDateTime create_at,
            LocalDateTime update_at
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user_id = user_id;
        this.create_at = create_at;
        this.update_at = update_at;
    }
}
