package com.spring.study.service;


import com.spring.study.domain.Users;
import com.spring.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    // userRepository 객체 정의
    private final UserRepository userRepository;

    // 생성자를 통한 의존성 주입
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUser(String userName) {
        Optional<Users> user = userRepository.findByUserName(userName);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        return user.get();
    }

}
