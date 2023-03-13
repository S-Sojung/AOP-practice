package shop.mtcoding.aopstudy.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAdvice {

    // 깃발 이름이 너무 길 경우 줄여줄 수 있다. -> 깃발에 별칭 주기
    @Pointcut("@annotation(shop.mtcoding.aopstudy.handler.aop.Hello)")
    public void hello() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }

    // @Before("hello()")
    // @After()
    @Around("hello()") // 전후를 제어한다
    public Object helloAdvice(ProceedingJoinPoint jp) throws Throwable {// 메서드
        // 정보들 다 있음!
        Object[] args = jp.getArgs();

        System.out.println("파라미터 사이즈 : " + args.length);
        for (Object arg : args) {
            if (arg instanceof String) {
                String username = (String) arg;
                System.out.println(username + " 님 안녕 ");
            }
        }
        // System.out.println("안녕!~ ");
        return jp.proceed(); // null return 시, 메서드 실행인 안됨 !!
    }

    // @Before("hello()")
    // public void helloAdvice() {
    // System.out.println("안녕안녕");
    // }

    // @Around("getMapping() || hello()")
    // public void getAdvice() {
    // System.out.println("헉헉");
    // // 단 이렇게 햇는데 안녕안녕이 안뜸 .. 왜?
    // // => 얘가 앞 뒤를 다 지배해서 그런듯.
    // }
}
