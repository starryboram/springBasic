package hello.core.scan.filter;

import java.lang.annotation.*;

// 이 아래의 3개가 붙은 경우 다 컴포넌트 스캔에 추가할 것임을 의미함
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
