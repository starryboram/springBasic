package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        // 검증해보기 memberService가 memberServiceImplement의 클래스의 isstance인지 물어보기
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 여태까지 인터페이스로 조회를 했는데, 이럴 경우 인터페이스의 구현체를 대상으로 조회

    // AppConfig 파일의 memberService를 안써도 된다. 왜냐하면 return에 나와있는 인스턴스 타입(MemberServiceImpl)을 보고 결정하기 때문에!
    // 물론 결정적인 코드를 적는 것은 좋은 것이 아니다. 역할과 구현을 구분해야 하고, 역할에 의존해야 하기 때문에 밑의 코드처럼 구현에 의존하는 코드를 작성하면 안 좋다.
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameX(){
       // MemberService xxxx = ac.getBean("xxxx", MemberService.class);// 없는 걸로 검색하면 에러 뜬다. 이 에러를 잡는 코드를 써야함.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class)); // 오른쪽에 있는 로직이 성공하면 왼쪽꺼가 에러가 터져야 한다.
    }
}
