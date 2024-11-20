package com.spring.study.domain;

import com.spring.study.dto.user.UserResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Entity

@Getter @Setter
@Table(name = "users")
@Data
@DynamicInsert
public class Users {
    // Pk
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    // 사용자 이름
    @Column(name = "user_name")
    private String userName;
    // 사용자 비밀번호
    private String password;

    public Users() {
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserResponseDTO toUserResponseDTO() {
        return new UserResponseDTO(this);
    }
}