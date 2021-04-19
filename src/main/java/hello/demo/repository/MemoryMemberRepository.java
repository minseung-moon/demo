package hello.demo.repository;

import hello.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Null 처리하는 방법 중 하나
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // findAny(), 하나라도 찾는것, 끝까지 찾아도 없으면 optional에 null이 포함되서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        // store를 비워준다
        store.clear();
    }
}
