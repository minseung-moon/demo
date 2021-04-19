package hello.demo.repository;

import hello.demo.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // jpa는 EntityManager로 동작한다
    // jpa를 그래들 해주면 알아서 추가(주입)가 된다(사용할 수 있다), 인젝션 받는다
    // jpa를 사용할려면 항상 Transaction이 있어야한다(service에 추가)
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist, 영속하다, 영구 저장하다
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // (찾을 데이터의 타입, 식별자)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name",name).getResultList();

        // list에서 첫번째 값 전달
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql을 활용, 테이블 대상으로 쿼리를 날리는것이 아닌 객체를 대상으로 쿼리를 날린다
        // Entity를 대상으로 쿼리를 날린다
        // Member엔티티를 조회해, Member m == Member as m
        // 객체 자체를 셀렉트 m
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
