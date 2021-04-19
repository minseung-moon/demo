package hello.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP는 Aspect애노테이션이 필요
@Aspect
// @Component로 등록해서 사용할 수 있지만, spring bean에 등록해서 사용하는 편을 권고한다
@Component
public class TimeTraceAop {

    // Around로 타켓팅("execution(* 패키지명..*(..))") ..*~은 특정 부위 하위의 전체, hello.demo.service..*(..)은 서비스 하위 전부
    @Around("execution(* hello.demo..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start : "+joinPoint.toString());
        try {
            // joinPoint.proceed() : 다음 메소드로 진행하게 해줌
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("End : "+joinPoint.toString() + " "+timeMs+"ms");
        }

    }
}
