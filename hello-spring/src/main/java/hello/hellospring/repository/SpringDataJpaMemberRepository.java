package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name); // 스프링 데이터 JPA가 구현체를 자동으로 만들어주고 빈 등록까지 해준다.
}
