package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // prototypeBean1.destory(); 라고 직접 호출해야함.
        ac.close(); // close 가 안됨.
    }

    // AnnotationConfigApplicationContext를 쓰면 @Component 쓰지 않아도 알아서 스프링 빈으로 등록해줘서 사용이 가능하다.
    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destory");
        }
    }
}
/*
* 싱글톤 빈: 스프링 컨테이너 생성 시점에 초기화 메서드가 실행됨
* 프로토타입 스코프 빈: 스프링 컨테이너에서 빈을 조회할 때 생성되고, 초기화 메서드도 실행
* 프로토타입 빈 2번 조회 => 완전히 다른 스프링 빈이 생성, 초기화도 2번 실행
* 스프링 컨테이너가 종료될 때 @PreDestory 같은 종료 메서드 실행 안됨
* */