package com.spring.study.useCase.service.user;


import com.spring.study.domain.user.Users;
import com.spring.study.exception.custom.AlreadyExistsException;
import com.spring.study.exception.custom.ResourceNotFoundException;
import com.spring.study.exception.message.UserErrorMessage;
import com.spring.study.infrastructure.repository.UserRepository;
import com.spring.study.useCase.service.user.dto.IUserRequestDTO;
import com.spring.study.useCase.service.util.jwt.JwtUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    // userRepository 객체 정의
    private final UserRepository userRepository;
    // jwtUtil 객체 정의
    private final JwtUtil jwtUtil;

    // 생성자를 통한 의존성 주입
    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Users getUser(String userName) {
        Optional<Users> user = userRepository.findByUserName(userName);
        // 찾는 유저가 존재하지 않으면 오류
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(UserErrorMessage.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Transactional
    public Users createUser(IUserRequestDTO createUserRequestDTO) {

        Optional<Users> u = userRepository.findByUserName(createUserRequestDTO.getUserName());

        // 유저가 없다면 정상 생성
        if (u.isEmpty()) {
            Users user = Users.builder()
                    .userName(createUserRequestDTO.getUserName())
                    .password(createUserRequestDTO.getPassword())
                    .build();
            return userRepository.save(user);
        }
        // 유저가 존재하면 오류 발생
        throw new AlreadyExistsException(UserErrorMessage.USER_ALREADY_EXISTS);
    }

    public String userLogin(IUserRequestDTO userDTO) {
        Optional<Users> users = userRepository.findByUserName(userDTO.getUserName());

        // 유저가 존재하지 않으면 오류
        if (users.isEmpty()) {
            throw new ResourceNotFoundException(UserErrorMessage.USER_NOT_FOUND);
        }
        // 비밀번호가 일치하지 않으면 오류
        else if (!users.get().getPassword().equals(userDTO.getPassword())) {
            throw new ResourceNotFoundException(UserErrorMessage.PASSWORD_NOT_MATCH);
        }
        // 토큰 생성
        return jwtUtil.createToken(users.get().getUserName(), users.get().getRole());
    }
}
