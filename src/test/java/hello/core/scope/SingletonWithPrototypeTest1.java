package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        //assertThat(count2).isEqualTo(2); // 생성시점에 주입된 애를 같이 쓰게 된다.
        assertThat(count2).isEqualTo(1); // prototypeBeanProvider사용
    }

    @Scope("singleton") //  싱글톤의 경우
    static class ClientBean{

        // private final PrototypeBean prototypeBean; // 생성시점에 주입
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){ // prototypeBean이 필요하다고 하면 이때 생성해서 호출함
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic(){
//            prototypeBean.addCount(); // 이미 생성시점에 주입된 프로토타입을 사용하게 됨
//            return prototypeBean.getCount();
//        }
        // 문제점: 프로로타입 빈을 주입 시점에만 새로 생성하는게 아니라, 사용할때마다 새로 생성해서 사용하고 싶다.
        // 싱글톤 빈은 생성시점에만 의존관계를 주입받기 때문에 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤빈과 함께 계속 유지된다.

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//        // private ObjectFactory<PrototypeBean> prototypeBeanProvider; ObjectProvider대신 ObjectFactory 써도 된다.
//        /*
//        ObjectFactory: 기능이 단순, 별도의 라이브러리 필요 없음, 스프링에 의존
//        ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요없음, 스프링에 의존
//        */
//
//        public int logic(){
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // getObject 하면 이때 찾아서 호출
//            // getObject를 통해서 항상 새로운 프로토타입 빈이 생성됨. Dependency Lookup(getObject호출 시 스프링 컨테이너를 통해 해당 빈을 찾아서 반환)
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }

        //  Provider.get을 통해서도 새로운 프로토타입 빈이 생성된다. 딱 DL기능만 수행하고 있고, 자바 표준이다.
        // 기능이 단순하기 때문에 단위 테스트를 만들거나 MOCK 코드를 만들기 훨씬 쉬워진다.
        // 스프링이 아닌 다른 컨테이너에서도 사용이 가능하다.
       @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init"+this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
