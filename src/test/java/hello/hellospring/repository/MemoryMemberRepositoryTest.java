package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

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

}
