package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 객체 생성한다음에
            networkClient.setUrl("http://hello-spring.dev"); // setting함
            return networkClient;
        }
    }
}
/*스프링 빈의 라이프 사이클
"객체 생성" -> "의존관계 주입"
객체의 초기화: 외부 db랑 다 연결된 상태를 의미함.
그래서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출되어야 한다.

<스프링 빈의 이벤트 라이프사이클> : 싱글톤에 관련된 이야기
스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
객체 생성과 초기화를 분리해야 한다.
생성자는 필수 정보를 받고 메모리 할당해서 객체를 생성하는 역할
초기화는 생상된 값을 활용해서 외부 connection 연결(무거움)
*/
