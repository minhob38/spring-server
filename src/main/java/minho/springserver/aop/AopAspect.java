package minho.springserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
//@Aspect
//@Component
public class AopAspect {
    @Aspect
    @Order(2) // aspect class 단위로 순서를 정의할 수 있기에, inner static class로 aspect class를 만듭니다.
    @Component
    public static class LogA {
        @Around("execution(* minho.springserver.controller..*(..))")
        public Object logA(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            log.info("aop around");
            log.info("aop around - signature {}", proceedingJoinPoint.getSignature());
            log.info("aop around - args {}", proceedingJoinPoint.getArgs());
            Object[] args = proceedingJoinPoint.getArgs();
            return proceedingJoinPoint.proceed(); // around에서 proceed가 실행되어야 target을 실행시킵니다.
        }

    }

    @Aspect
    @Order(1)
    @Component
    public static class LogB {
        /* point cut으로 aop 적용 지점을 정의할 수 있습니다. */
        @Pointcut("execution(* minho.springserver.controller..*(..))")
        private void controllerPointCut() {}

        /* point cut method이름을 넣어줍니다. */
        @Around("controllerPointCut()")
        public Object logB(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            log.info("aop around - pointcut");
            return proceedingJoinPoint.proceed(); // around에서 proceed가 실행되어야 target을 실행시킵니다.
        }
    }

    @Aspect
    @Order(1)
    @Component
    public static class Clock {
        private  long startedAt;
        private  long finishedAt;

        @Pointcut("execution(* minho.springserver.controller..*(..))")
        private void controllerPointCut() {}

        /* point cut method이름을 넣어줍니다. */
        @Before("controllerPointCut()")
        public void beforeTime(JoinPoint joinPoint) throws Throwable {
            log.info("aop around - before");
            startedAt = System.currentTimeMillis();
        }

        /* point cut method이름을 넣어줍니다. */
        @After("controllerPointCut()")
        public void afterTime(JoinPoint joinPoint) throws Throwable {
            finishedAt = System.currentTimeMillis();
            log.info("aop around - after");
            log.info("aop around - response time {}", finishedAt - startedAt);
        }
    }
}
