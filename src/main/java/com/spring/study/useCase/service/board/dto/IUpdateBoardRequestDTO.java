package com.spring.study.useCase.service.board.dto;

public interface IUpdateBoardRequestDTO {

    String getTitle();

    String getContent();

    Long getUserId();

    String getPassword();
}
