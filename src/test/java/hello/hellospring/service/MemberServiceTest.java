package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {


    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //DB의 데이터를 하나의 메서드 끝날 때 마다 메모리를 지워준다.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {                   //테스트는 한글로 적어도 된다.
        //given (이런 상황이 주어져서)  ->  이 데이터를 기반으로 하는구나
        Member member = new Member();
        member.setName("hello");

        //when  (이걸로 실행했을 때) -> 이걸 검증하는구나
        Long saveId  = memberService.join(member);
        //then  (그때 이것이 나와야해!) ->  검증부 구나
        Member findMember = memberService.findOne(saveId).get();

        //멤버의 이름이 findMember의 이름과 같냐
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //테스트는 정상 flow도 중요하지만 예외 flow가 중요하다!
    @Test
    public void 중복_회원_예약(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when

        //첫번 째 조인인
       memberService.join(member1);
        //이렇게 넣으면 예외가 터져야 한다. 두번 째 조인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //이 방법으로도 예외를 테스트 해 볼수있다.
//        try {
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then
    }

    private void fail() {
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}