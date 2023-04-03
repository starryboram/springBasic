package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    // @Qualifier("~~") 여기서의 ~~는 컴파일 시 오류 검증이 안된다. 그래서 어노테이션을 만들어서 체크해주는 것이 좋다.
    //MainDiscountPolicy을 쓰면 위에 있는 기능이 다 동작한다.

}
