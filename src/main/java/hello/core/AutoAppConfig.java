package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정정보니까 configuration을 써주고
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)
)
// 자동으로 스캔해와야 하니까 ComponentScan을 써줌(@Component라고 써져있는애를 자동으로 빈 등록해줌)
// 근데 여기서 빼고 싶은애들 => Filter을 사용. 여기서는 Configuraion.class를 filter처리했는데, 보통은 처리 안함
// 다만 예제코드를 유지하고 싶어서 AppConfig에 붙은 애 때문에 한시적으로 뺌.
public class AutoAppConfig {


}
