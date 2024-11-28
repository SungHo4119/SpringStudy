package com.spring.study.infrastructure.controller.user.dto;

import static com.spring.study.exception.message.UserErrorMessage.PASSWORD_LENGTH;
import static com.spring.study.exception.message.UserErrorMessage.PASSWORD_PATTERN;
import static com.spring.study.exception.message.UserErrorMessage.USER_NAME_LENGTH;
import static com.spring.study.exception.message.UserErrorMessage.USER_NAME_NOT_BLANK;

import com.spring.study.useCase.service.user.dto.ICreateUserRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateUserRequestDTO implements ICreateUserRequestDTO {

    // 이름
    @NotBlank(message = USER_NAME_NOT_BLANK)
    @Size(min = 4, max = 10, message = USER_NAME_LENGTH)
    private String userName;

    // 비밀번호
    @Size(min = 8, max = 15, message = PASSWORD_LENGTH)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]+$", message = PASSWORD_PATTERN)
    private String password;

    @Builder
    public CreateUserRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
