package com.spring.study.infrastructure.controller.user.dto;

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
    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 10, message = "User name must be between 4 and 10 characters")
    private String userName;

    // 비밀번호
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]+$", message = "Password must contain at least one letter, one number and one special character")
    private String password;

    @Builder
    public CreateUserRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
