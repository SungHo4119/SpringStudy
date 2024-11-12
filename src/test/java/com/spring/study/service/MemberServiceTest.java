package com.spring.study.service;


import static org.assertj.core.api.Assertions.*;

import com.spring.study.domain.Member;
import com.spring.study.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
class MemberServiceTest {

    MemberService memberService = new MemberService();

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEatch() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원_가입() {
        // given
        Member member = new Member();
        member.setName("이름");
        // when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember).isEqualTo(member);
    }
    @Test
    public void 중복_회원_예외(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try{
//            memberService.join(member2);
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}