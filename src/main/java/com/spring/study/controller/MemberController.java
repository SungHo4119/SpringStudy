package com.spring.study.controller;

import com.spring.study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller 어노테이션을 붙여주면 스프링 컨테이너에 등록이 된다.
@Controller
public class MemberController {
    private final MemberService memberService;

    // 생성자에 `@Autowired` 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
