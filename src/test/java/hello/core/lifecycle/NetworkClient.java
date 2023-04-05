package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 이 둘의 인터페이스는 스프링 전용 인터페이스이다 보니, 코드가 스프링 전용 인터페이스에 의존하게 된다.
// 초기화, 소멸 메서드의 이름을 변경할 수 없고, 외부 라이브러리에 적용이 불가하다.
public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;
    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: "+url);
    }

    public void call(String message){
        System.out.println("call: " + url + "message = " + message);
    }

    // 서비스 종료 시 호출
     public void disconnect(){
         System.out.println("close: " + url);
     }

    @Override
    public void afterPropertiesSet() throws Exception { // 프로퍼티 셋팅이 끝나면(의존관계 주입이 끝나면) 호출하겠다는 뜻.
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception { // 종료될 때 호출
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}

