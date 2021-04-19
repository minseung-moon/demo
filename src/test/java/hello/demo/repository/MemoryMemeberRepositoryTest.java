package hello.demo.repository;

import hello.demo.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemeberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메소드가 끝 날때마다 동작한다, callbackmethod
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    // 직접 실행해 본다(만든 메소드)
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // findById의 반환값이 optional이다, optional은 get메소드를 사용할 수 있다, 추천 안함
        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = "+(result == member));
        // (기대하는것, 비교 값), 정상 처리되면 green, 오류가 나면 red
        // JUnit
        // Assertions.assertEquals(member, result);
        // assertj, member가 result랑 똑같은지 물어봄
        // Assertions에 oprion+Enter로 static import를 하면 메소드를 바로 호출 할 수 있다
        assertThat(member).isEqualTo(result);
    }

    // 전체를 돌렸을 때 findById에서 에러가 나는데 순서를 봐야 된다
    // 다른 메소드에서 값을 넣어주었기 때문에 이전 데이터로 인해 오류가 날수 있다
    // 그렇기 때문에 test가 끝날 때 이전 데이터를 clean해줘야 한다, 상단에 afterEach를 작성
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift+f6으로 이름 변경 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
