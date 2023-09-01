package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // DI 의존성 주입(같은 인스턴스를 사용할 수 있게끔 만듦)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 저장소 비우기
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
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

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }

}