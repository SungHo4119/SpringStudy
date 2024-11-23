// 하위 패키지에 있는 모든 파일을 스캔하여 빈으로 등록한다.
package com.spring.study;

//@ComponentScan 이 있는걸 찾아서 등록?한다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // Auditing 기능 활성화
public class SpringStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
    }
}
