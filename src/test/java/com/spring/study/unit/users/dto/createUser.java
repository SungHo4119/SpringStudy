package com.spring.study.unit.users.dto;

import com.spring.study.dto.user.CreateUserRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class createUser {

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
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO("validName","DlaSumin2@");
        // Validator 인스턴스를 사용하여 userRequestDTO 객체의 필드 값 검증
        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(userRequestDTO);
        // violations는 검증된 결과를 담고 있음 비어있으면 통과
        assertTrue(violations.isEmpty());
    }

    @Test
    public void 유저이름짧음() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO("abc","DlaSumin2@");

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(userRequestDTO);
        // violations.size()는 검증된 결과의 개수를 반환 (오류 갯수 )
        assertEquals(1, violations.size());
        // violations.iterator().next().getMessage()는 첫 번째 오류 메시지를 반환
        assertEquals("User name must be between 4 and 10 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void 유저이름너무김() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO("thisNameIsTooLong","DlaSumin2@");

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertEquals(1, violations.size());
        assertEquals("User name must be between 4 and 10 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void 비밀번호짧음() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO("validName","aA1@");

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertEquals(1, violations.size());
        assertEquals("Password must be between 8 and 15 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void 비밀번호너무김() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO("validName","aA1@aA1@aA1@aA1@aA1@aA1@");

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertEquals(1, violations.size());
        assertEquals("Password must be between 8 and 15 characters", violations.iterator().next().getMessage());
    }
    @Test
    public void 비밀번호정규식안맞음() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO("validName","1234567890");

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertEquals(1, violations.size());
        assertEquals("Password must contain at least one letter, one number and one special character", violations.iterator().next().getMessage());
    }

    @Test
    public void 오류가두개인경우체크하는법() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO("aaa","1234567890");

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(userRequestDTO);
        // 오류 갯수 확인
        assertEquals(2, violations.size());
        for (ConstraintViolation<CreateUserRequestDTO> violation : violations) {
            // getPropertyPath()는 오류가 발생한 필드의 경로를 반환
            if(violation.getPropertyPath().toString().equals("userName"))
                // getMessage()는 오류 메시지를 반환
                assertEquals("User name must be between 4 and 10 characters", violation.getMessage());
            else if(violation.getPropertyPath().toString().equals("password"))
                assertEquals("Password must contain at least one letter, one number and one special character", violation.getMessage());
        }
    }
}