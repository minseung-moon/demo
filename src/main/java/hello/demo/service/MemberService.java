package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
// spring에 올라올 때 spring container에 스프링 멤버서비스에 등록
@Transactional
// JPA를 사용할 때 필수, 필요한 메소드에만 해줘도 된다
public class MemberService {
    // command + shif + T를 해주면 자동으로 test파일을 만들수 있다

    // 회원 서비스를 위한 회원 레포지토리
    private final MemberRepository memberRepository;
    // DI, Dependency Injection,외부에서 할당, 의존성 주입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원X
        // command + option + v
        // controll + t => 9
        extracted(member);

        memberRepository.save(member);
        return member.getId();

//        // 시간 측정
//        long start = System.currentTimeMillis();
//
//        try {
//            // 같은 이름이 있는 중복 회원X
//            // command + option + v
//            // controll + t => 9
//            extracted(member);
//
//            memberRepository.save(member);
//            return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish-start;
//            System.out.println("join = "+timeMs + "ms");
//        }
    }
    // 회원 중복 검사 메소두
    private void extracted(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();

//        long start = System.currentTimeMillis();
//
//        try {
//            return memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish-start;
//            System.out.println("findMembers : "+timeMs+"ms");
//        }

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
