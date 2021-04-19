package hello.demo.repository;

import hello.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원을 저장
    // 값이 없으면 null이 나오는데 null 처리하는 방법으로 java8에 출시
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
