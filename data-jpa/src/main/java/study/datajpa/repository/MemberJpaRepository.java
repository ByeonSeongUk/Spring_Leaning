package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @TITLE 일반 JPA 사용 방식
 */
@Repository
public class MemberJpaRepository {

    @PersistenceContext // JPA를 사용하려면 꼭 필요하다.
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
