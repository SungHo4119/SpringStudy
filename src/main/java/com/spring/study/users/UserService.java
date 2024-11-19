package com.spring.study.users;


import com.spring.study.domain.Users;
import com.spring.study.exception.error.AlreadyExistsException;
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
        // 찾는 유저가 존재하지 않으면 오류
        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return user.get();
    }

    public Users createUser(Users user) {
        Optional<Users> u = userRepository.findByUserName(user.getUserName());
        // 유저가 없다면 정상 생성
        if(u.isEmpty()) {
            return userRepository.save(user);
        }
        // 유저가 존재하면 오류 발생
        throw new AlreadyExistsException("User already exists");
    }
}
