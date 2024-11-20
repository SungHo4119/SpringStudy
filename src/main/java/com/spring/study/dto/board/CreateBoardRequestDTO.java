package com.spring.study.dto.board;

import com.spring.study.domain.Board;
import com.spring.study.domain.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateBoardRequestDTO {
    @NotBlank
    @Size(min = 1, max = 20, message = "Title must be between 1 and 20 characters")
    private String title;
    @NotBlank
    @Size(min = 1, max = 500, message = "Content must be between 1 and 500 characters")
    private String content;
    @NotBlank
    private Long userId;

    public CreateBoardRequestDTO(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Board toEntity() {
        Users user = new Users();
        user.setId(userId);
        return new Board(title, content, user);
    }


}
