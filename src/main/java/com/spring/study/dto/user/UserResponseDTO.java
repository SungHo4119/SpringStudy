package com.spring.study.dto.user;

import com.spring.study.domain.Users;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String userName;

    public UserResponseDTO(Users user) {
        this.id = user.getId();
        this.userName = user.getUserName();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }


}
