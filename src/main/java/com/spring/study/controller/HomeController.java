package com.spring.study.controller;

import com.spring.study.domain.Member;
import com.spring.study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class HomeController {
    private final MemberService memberService;
    @Autowired
    public HomeController(MemberService memberService){
        this.memberService = memberService;
    }


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String crate(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        // list 한번에 만들기 memberService.findMembers(); 뒤에 커맨드 + 옵션 + v
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
