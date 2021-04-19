package hello.demo.repository;

import hello.demo.domain.Member;
import org.apache.juli.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 상속 받을 때는 extends를 사용(objecType, idType)
// JpaRepository를 상속 받으면 구현체를 자동으로 만들어진다
// spring data jap가 알아서 만들어 준다(기본 Crud 인터페이스가 형성되어있다), 페이징 처리도 해줌
public interface SpringDataJapMemberRepository extends JpaRepository<Member, Log>, MemberRepository {

    // naming 규칙에 맞춰서 작성 시, findByNameAndId,...
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
