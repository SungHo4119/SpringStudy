package com.spring.study.infrastructure.controller.user.dto;

import com.spring.study.useCase.service.user.dto.IUserPublicDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserPublicDTO implements IUserPublicDTO {

    private Long id;
    private String userName;

    @Builder
    public UserPublicDTO(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
