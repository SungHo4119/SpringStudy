package com.spring.study.dto.user;

import com.spring.study.domain.Users;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String userName;

    @Builder
    public UserResponseDTO(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }


}
