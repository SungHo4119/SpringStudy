package com.spring.study.dto.board;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateBoardRequestDTO {

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
