package com.spring.study.dto.board;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateBoardRequestDTO {

    @Size(min = 1, max = 20, message = "Title must be between 1 and 20 characters")
    private String title;

    @Size(min = 1, max = 500, message = "Content must be between 1 and 500 characters")
    private String content;

    @NotNull(message = "User ID must not be null")
    @Positive(message = "User ID must be positive") // 양수
    private Long userId;

    @Builder
    public CreateBoardRequestDTO(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
