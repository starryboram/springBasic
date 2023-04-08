package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {
    private String uuid;
    private String requestURL;// request가 없어서 에러가 난다.(먼저 웹 요청이 들어와야 하는데, 들어오기도 전에 호출하니까 에러가남)

    //requestURL의 경우 언제 생성되는지 알 수 없어서 setter로 만들어서 보려고 함.
    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"]"+message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString(); // 겹칠 확률이 로또*로또*로또*로또*로또
        System.out.println("["+uuid+"] request scope bean create: " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close: " + this);
    }
}
