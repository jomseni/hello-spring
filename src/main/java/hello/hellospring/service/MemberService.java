package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //회원 서비스를 하려면 회원 데이터가 필요하므로 repository가 필요하다! 객체 생성해준다
    //객체 생성할땐 클래스의 이름만 들어갈 수 있다.
    //interface의 이름은 들어갈수 없으므로  객체 생성자의 이름은 MemoryMemberRepository가 되는 것이다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원가입//
    public Long join(Member member){

        //memberRepository.findByName(member.getName()); 를 적은 후에 컨트롤 + alt + v를 해서 바꿔준다. 멤버레퍼지토리에서 멤버를 찾는다.

        //결과값을 꺼내오고 그 값이 있을경우에 throw new IllegalStateException을 실행한다!
        //Optional을 사용했기때문에 이런 문법을 사용 할 수 있는것이다
        //반환값은 Optional<member> 이다
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);    // 1. 중복 회원검증
        memberRepository.save(member);      // 2. 없는 데이터면 새로 데이터를 저장한다
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                //만약 이름이 중복된다면  IllegalStateException 예외 처리
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회//
    public List<Member> findMembers(){

        //List형식이므로 리턴 값은 그냥 받으면 된다.
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }
}
