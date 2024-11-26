package com.spring.study.useCase.service.board.dto;

public interface ICreateBoardRequestDTO {

    String getTitle();

    String getContent();

    Long getUserId();
}
