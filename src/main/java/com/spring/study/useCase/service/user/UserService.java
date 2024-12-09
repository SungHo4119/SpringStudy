package com.spring.study.useCase.service.user;


import com.spring.study.domain.Users;
import com.spring.study.exception.custom.AlreadyExistsException;
import com.spring.study.exception.custom.ResourceNotFoundException;
import com.spring.study.exception.message.UserErrorMessage;
import com.spring.study.infrastructure.repository.UserRepository;
import com.spring.study.useCase.service.user.dto.ICreateUserRequestDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(UserErrorMessage.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Transactional
    public Users createUser(ICreateUserRequestDTO createUserRequestDTO) {

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
}
