package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 이 둘의 인터페이스는 스프링 전용 인터페이스이다 보니, 코드가 스프링 전용 인터페이스에 의존하게 된다.
// 초기화, 소멸 메서드의 이름을 변경할 수 없고, 외부 라이브러리에 적용이 불가하다.
public class NetworkClient {
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

    public void init(){ // 빈 등록 초기화(내가 원하는대로 바꿀 수 있다)
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close(){ // 빈 소멸
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

