package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 제대로 @bean에 의해 잘 등록이 되었는지 확인해보는 test파일 생성
class ApplicationContextInfoTest {
    //public 생략 가능 junit 업데이트 후

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // ctrl + alt + v
        for (String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName=" + beanDefinitionName + "object=" + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // ctrl + alt + v
        for (String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);  //beanDefinition : bean 하나하나에 대한 메타 정보를 담고 있음

            // Bean 확인이 필요 할 때
            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 bean(우리가 만든 빈)
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 BEAN
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName=" + beanDefinitionName + "object=" + bean);
            }
        }
    }
}
