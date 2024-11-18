package com.spring.study.controller;

import com.spring.study.domain.Users;
import com.spring.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

    @GetMapping("/findName")
    public ResponseEntity<Users>  getUser(
        // name 파라미터를 받아옴
        @RequestParam(value = "name") String name
    ) {
        Users user = userService.getUser(name);

        return ResponseEntity.ok(user);
    }


}
