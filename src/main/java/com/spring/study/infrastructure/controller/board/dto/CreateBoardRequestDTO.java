package com.spring.study.infrastructure.controller.board.dto;

import com.spring.study.exception.message.BoardErrorMessage;
import com.spring.study.exception.message.PublicErrorMessage;
import com.spring.study.useCase.service.board.dto.ICreateBoardRequestDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateBoardRequestDTO implements ICreateBoardRequestDTO {

    @Size(min = 1, max = 20, message = BoardErrorMessage.TITLE_LENGTH)
    private String title;

    @Size(min = 1, max = 500, message = BoardErrorMessage.CONTENT_LENGTH)
    private String content;

    @NotNull(message = PublicErrorMessage.ID_NOT_NULL)
    @Positive(message = PublicErrorMessage.ID_POSITIVE) // 양수
    private Long userId;

    @Builder
    public CreateBoardRequestDTO(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
