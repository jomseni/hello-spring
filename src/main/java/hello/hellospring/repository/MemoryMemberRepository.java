package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //sequence 는 key값을 순서대로 정해주는 것!

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {     //
        return Optional.ofNullable(store.get(id)); //찾는 값의 id 가 없을 경우의 기능을 설정해주기 위해 Optional.ofNullable를 사용한다
    }


    //찾는 name이 있을 경우에만 반환한다
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
