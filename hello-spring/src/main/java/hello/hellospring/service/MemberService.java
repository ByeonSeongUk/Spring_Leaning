package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // JPA를 사용하려면 항상 Service에 있어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { // new로 새로운 인스턴스를 만들지 말고 외부에서 넣어주도록 만듦
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 X
        memberRepository.findByName(member.getName())
//                .orElseGet(); // 값이 있으면 꺼내고 아니면 다른 동작
            .ifPresent(m -> { // 해당 result에 값이 있으면 동작
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     * @return List<Member>
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     * @param memberId
     * @return Member
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
