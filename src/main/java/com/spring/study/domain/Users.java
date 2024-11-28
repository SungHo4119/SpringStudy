package com.spring.study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok 기본 생성자 추가 JPA 사용하기위해 기본 생성자 추가
public class Users {

  // Pk
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // 사용자 이름
  @Column(name = "user_name")
  private String userName;
  // 사용자 비밀번호
  private String password;

  @Builder
  public Users(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }
}