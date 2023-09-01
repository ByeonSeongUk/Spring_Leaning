package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용
 */
//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return null;
    }

    // null이 반환될 가능성이 있으면 Optional로 감싼다.
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 파라미터로 넘어온 name을 map에서 루프를 돌면서 찾으면 반환, 없으면 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 저장소 비우기
    public void clearStore() {
        store.clear();
    }
}
