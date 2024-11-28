package com.spring.study.unit.dto.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spring.study.exception.message.BoardErrorMessage;
import com.spring.study.infrastructure.controller.board.dto.CreateBoardRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class createBoard {

    // 객체의 상태나 값이 유효한지 검사하는 인터페이스
    private Validator validator;

    @BeforeEach
    public void setUp() {
        // buildDefaultValidatorFactory() 메서드를 통해 기본 ValidatorFactory 인스턴스를 얻음
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // ValidatorFactory 인터페이스의 getValidator() 메서드를 통해 Validator 인스턴스를 얻음
        validator = factory.getValidator();
    }

    @Test
    public void 성공() {
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO("validTitle", "validContent", 1L);
        // Validator 인스턴스를 사용하여 userRequestDTO 객체의 필드 값 검증
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        // violations는 검증된 결과를 담고 있음 비어있으면 통과
        assertTrue(violations.isEmpty());
    }

    @Test
    public void 제목이름짧음() {
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO("", "validContent", 1L);
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        assertTrue(violations.size() == 1);
        assertEquals(BoardErrorMessage.TITLE_LENGTH, violations.iterator().next().getMessage());
    }

    @Test
    public void 제목이름길음() {
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO(
                "123456789012345678901",
                "validContent",
                1L
        );
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        assertTrue(violations.size() == 1);
        assertEquals(BoardErrorMessage.TITLE_LENGTH, violations.iterator().next().getMessage());
    }

    @Test
    public void 본문내용짧음() {
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO("validTitle", "", 1L);
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        assertTrue(violations.size() == 1);
        assertEquals(BoardErrorMessage.CONTENT_LENGTH, violations.iterator().next().getMessage());
    }

    @Test
    public void 본문내용김() {
        String str = "";
        for (int i = 0; i < 501; i++) {
            str += "a";
        }
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO(
                "validTitle",
                str,
                1L
        );
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        assertTrue(violations.size() == 1);
        assertEquals(BoardErrorMessage.CONTENT_LENGTH, violations.iterator().next().getMessage());
    }

    @Test
    public void 유저아이디가없음() {
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO("validTitle", "validContent", null);
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        assertTrue(violations.size() == 1);
        assertEquals(BoardErrorMessage.USER_ID_NOT_NULL, violations.iterator().next().getMessage());
    }

    @Test
    public void 유저아이디가음수() {
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO("validTitle", "validContent", -1L);
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        assertTrue(violations.size() == 1);
        assertEquals(BoardErrorMessage.USER_ID_POSITIVE, violations.iterator().next().getMessage());
    }

    @Test
    public void 유저아이다가0일때() {
        CreateBoardRequestDTO boardRequestDTO = new CreateBoardRequestDTO("validTitle", "validContent", 0L);
        Set<ConstraintViolation<CreateBoardRequestDTO>> violations = validator.validate(boardRequestDTO);
        assertTrue(violations.size() == 1);
        assertEquals(BoardErrorMessage.USER_ID_POSITIVE, violations.iterator().next().getMessage());
    }
}
