package com.spring.study.infrastructure.controller.board.dto;

import com.spring.study.useCase.service.board.dto.IUpdateBoardRequestDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateBoardRequestDTO implements IUpdateBoardRequestDTO {

    private String title;
    private String content;
    private Long userId;
    private String Password;

    @Builder
    public UpdateBoardRequestDTO(String title, String content, Long userId, String Password) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.Password = Password;
    }
}
