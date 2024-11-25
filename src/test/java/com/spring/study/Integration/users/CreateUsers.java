package com.spring.study.Integration.users;

import static com.spring.study.exception.message.UserErrorMessage.USER_ALREADY_EXISTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.spring.study.domain.Users;
import com.spring.study.dto.user.CreateUserRequestDTO;
import com.spring.study.exception.custom.AlreadyExistsException;
import com.spring.study.repository.UserRepository;
import com.spring.study.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CreateUsers {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 회원_가입() {
        // given
        CreateUserRequestDTO user = new CreateUserRequestDTO("test", "1234");
        // when
        Users createUser = userService.createUser(user);
        //then
        Users findUser = userRepository.findById(createUser.getId()).get();
        assertThat(createUser).isEqualTo(findUser);
    }

    @Test
    void 회원_아이디_중복() {
        // given
        CreateUserRequestDTO user = new CreateUserRequestDTO("test", "1234");
        // when
        Users createUser = userService.createUser(user);
        //then
        AlreadyExistsException e = assertThrows(AlreadyExistsException.class, () -> userService.createUser(user));
        assertThat(e.getMessage()).isEqualTo(USER_ALREADY_EXISTS);
    }

}
