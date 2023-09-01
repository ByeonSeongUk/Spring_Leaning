package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//
/**
 * @SpringBootTest: 스프링 컨테이너와 테스트를 함께 실행한다.
 * @Transactional : 테스트 케이스에 해당 어노테이션 사용시 테스트 끝난 후 롤백이 된다. -> DB에 데이터가 남지 않음
 *                  @Commit을 메서드에 붙이면 롤백 안됨
 */
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Commit // 해당 애노테이션을 붙이면 롤백 안됌
    void 회원가입() { // 한글로 써도 됨
        // given - 어떤 값이 들어가는지
        Member member = new Member();
        member.setName("spring");

        // when - 어떤걸 검증하는지
        Long saveId = memberService.join(member);

        // then - 어떤 결과가 나오는지
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 위 assertThrows로 간단하게 사용 가능
//        try {
//            memberService.join(member2);
//            fail();
//        } catch(IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.")
//        }

    }

}