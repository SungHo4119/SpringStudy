// 하위 패키지에 있는 모든 파일을 스캔하여 빈으로 등록한다.
package com.spring.study;

//@ComponentScan 이 있는걸 찾아서 등록?한다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
SpringStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudyApplication.class, args);
	}

}
