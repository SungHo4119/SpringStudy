package com.spring.study.dto.board;

import com.spring.study.exception.message.BoardErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateBoardRequestDTO {

    @Size(min = 1, max = 20, message = BoardErrorMessage.TITLE_LENGTH)
    private String title;

    @Size(min = 1, max = 500, message = BoardErrorMessage.CONTENT_LENGTH)
    private String content;

    @NotNull(message = BoardErrorMessage.USER_ID_NOT_NULL)
    @Positive(message = BoardErrorMessage.USER_ID_POSITIVE) // 양수
    private Long userId;

    @Builder
    public CreateBoardRequestDTO(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
