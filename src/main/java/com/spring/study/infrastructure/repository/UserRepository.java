package com.spring.study.infrastructure.repository;

import com.spring.study.domain.user.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String userName);
}
