package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    //매우 중요한 부분!
    //테스트 할 때 메서드의 실행순서가 의존관계없이 실행되므로 객체생성이 중복되어 오류가 날수있으므로 한번의 메서드 끝날때마다 afterEach메서드를 사용해 store와 repository를 비워주는 afterEach메서드를 정의해서 각각의 메서드를 잘 테스트 할 수있게 해준다
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        //리포지 토리에 멤버를 저장한다.
        repository.save(member);


        //리포지토리에 잘 저장되었는지 확인해보기! getId를 이용해서!   저장할땐 메인메서드에선 setId로 저장한다 test기때문에 getId 사용
        //DB에 저장된걸 꺼내오기  => 변수 result
        Member result = repository.findById(member.getId()).get();

        //new로 저장한거랑 DB에서 꺼내온거랑 데이터가 같으면 참으로 테스트한다.
        //Assertions를 import할 때 assertj로 해야 assertThat을 사용할 수있다.

        //이렇게 하면 예상한 값이 null로서 오류가난다
        //Assertions.assertThat(member).isEqualTo(null);

        Assertions.assertThat(member).isEqualTo(result);
    }

    //
    @Test
    public void findByName(){
        //spring1 회원이 가입 되어짐
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //spring2 회원이 가입 되어짐
        //member1 복붙 후  shift + f6을 이용해서 이름 member2로 바꿔주기
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //findByname이 잘 작동되는지 확인하기
        //repository에서 꺼내와서(get) result로 저장하기
        Member result = repository.findByName("spring1").get();

        //꺼내온 result는 member1과 같냐 라고 테스트 해보는 것이다!
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        //리포지토리에 member1 회원가입 정보 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //리포지토리에 member2 회원가입 정보 저장
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        //findAll은 List를 이용해 result를 받는다!
        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
