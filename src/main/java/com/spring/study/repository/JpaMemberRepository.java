package com.spring.study.repository;

import com.spring.study.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    // 스프링 부트가 엔티티 메니저에 레파지도리를 주입 해줌
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    public Member save(Member member) {
        // 저장
        em.persist(member);
        return member;
    }
    public Optional<Member> findById(Long id) {
        // 조회
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() {
        // sql
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name) {
        // Member 객체를 대상으로 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList().stream().findAny();
    }
}