package com.spring.study.useCase.service.board.dto;

import java.time.LocalDateTime;

public interface IBoardPublicDTO {

    Long getId();

    String getTitle();

    String getContent();

    Long getUser_id();

    LocalDateTime getCreate_at();

    LocalDateTime getUpdate_at();

}
