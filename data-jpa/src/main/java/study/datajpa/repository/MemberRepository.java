package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

/**
 * @TITLE Spring Data JPA 사용 방식
 * @COMMENT: JpaRepository<>에 웬만한 것들이 다 구현되어 있다.
 */
public interface MemberRepository extends JpaRepository<Member, Long> { // <TYPE(객체), PK TYPE(ID 타입)>
}
