package com.spring.study.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
    // 이름
    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 10, message="User name must be between 4 and 10 characters")
    private String userName;
    // 비밀번호
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]+$", message = "Password must contain at least one letter, one number and one special character")
    private String password;

    // 모든 필드를 초기화하는 생성자
    public UserRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

}