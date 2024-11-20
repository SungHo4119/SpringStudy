package com.spring.study.controller.user;

import com.spring.study.dto.user.CreateUserRequestDTO;
import com.spring.study.dto.user.UserResponseDTO;
import com.spring.study.service.UserService;
import com.spring.study.domain.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
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
    public ResponseEntity<UserResponseDTO>  getUser(
        // name 파라미터를 받아옴
        @RequestParam(value = "name") String name
    ) {
        Users user = userService.getUser(name);

        return ResponseEntity.ok(user.toUserResponseDTO());
    }

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
        // RequestBody로 Users 객체를 받아옴
       @Valid @RequestBody CreateUserRequestDTO userDTO
    ) {
        // requestDto를 User 객체로 변환
        Users user = userService.createUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(user.toUserResponseDTO());
    }
}
