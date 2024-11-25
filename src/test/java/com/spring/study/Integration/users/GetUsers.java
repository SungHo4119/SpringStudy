package com.spring.study.Integration.users;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.study.domain.Users;
import com.spring.study.dto.user.CreateUserRequestDTO;
import com.spring.study.repository.UserRepository;
import com.spring.study.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GetUsers {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 회원_조회() {
        // given
        CreateUserRequestDTO user = new CreateUserRequestDTO("test", "1234");
        Users createUser = userService.createUser(user);
        // when
        Users getUser = userService.getUser(user.getUserName());
        // then
        assertThat(getUser.getUserName()).isEqualTo("test");
    }
}
