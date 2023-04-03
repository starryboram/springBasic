package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
//자동주입대상을 처리하는 방법 3가지
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
    static class TestBean{
        @Autowired(required = false) // (required = true)면 오류남. -> 자동주입할 대상이 없으면 수정자 메서드 자체가 호출이 안된다.
        public void setNoBean1(Member noBean1){ // 스프링으로 관리되지 않는 애를 넣음
            System.out.println("noBean1= " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ // 호출은 되지만 null로 들어온다.
            System.out.println("noBean2= " + noBean2); // noBean2= null
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){ // 스프링빈이 없으면 optional.empty라고 나옴.
            System.out.println("noBean3= " + noBean3); // noBean3= Optional.empty
        }
    }
}
