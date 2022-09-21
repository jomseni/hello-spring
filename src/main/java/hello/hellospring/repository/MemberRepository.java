package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //repository의 네가지 기능을 만들어준다!
    //Optional은 findById,findByName 가져올 때 값이 null일 수 있는데, 요즘엔 null 반환 처리할때 Optional을 사용 한 방법을 사용한다!
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
