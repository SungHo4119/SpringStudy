package com.spring.study.infrastructure.controller.user;

import com.spring.study.domain.Users;
import com.spring.study.infrastructure.controller.user.dto.CreateUserRequestDTO;
import com.spring.study.infrastructure.controller.user.dto.UserPublicDTO;
import com.spring.study.useCase.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // userService 객체 정의
    private final UserService userService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 이름으로 유저 조회
    @GetMapping
    public ResponseEntity<UserPublicDTO> getUser(
            // name 파라미터를 받아옴
            @RequestParam(value = "name") String name
    ) {
        Users user = userService.getUser(name);

        UserPublicDTO userResponseDTO = UserPublicDTO.builder()
                .id(user.getId()).userName(user.getUserName()).build();

        return ResponseEntity.ok(userResponseDTO);
    }

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserPublicDTO> createUser(
            // RequestBody로 Users 객체를 받아옴
            @Valid @RequestBody CreateUserRequestDTO userDTO
    ) {
        // requestDto를 User 객체로 변환
        Users user = userService.createUser(userDTO);

        UserPublicDTO userResponseDTO = UserPublicDTO.builder()
                .id(user.getId()).userName(user.getUserName()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

}
