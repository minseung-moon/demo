package hello.demo.service;

import hello.demo.aop.TimeTraceAop;
import hello.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    //jdbc template
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // jpa
//    @PersistenceContext
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // data-jap
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    // spring bean에 등록하라는 뜻
    // 아래의 로직을 실행하게 됨
    public MemberService memberService() {
        // member repository가 필요
        // spring bean에 등록된 memberRepository가 등록
//        return new MemberService(memberRepository());

        // 위에서 의존성 주입을 받은 변수를 사용
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return  new TimeTraceAop();
//    }
}
