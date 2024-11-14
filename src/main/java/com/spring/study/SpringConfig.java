package com.spring.study;

import com.spring.study.repository.JpaMemberRepository;
import com.spring.study.repository.MemberRepository;
import com.spring.study.repository.MemoryMemberRepository;
import com.spring.study.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    JPA
//    private  EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 스프링 빈 등록
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }

//    @Bean
//    public MemberRepository memberRepository() {
        // No DataBase
        // return new MemoryMemberRepository();
        // JPA
        // return new JpaMemberRepository(em);

//    }
}
