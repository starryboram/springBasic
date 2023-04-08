package hello.core.logdemo;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

   // private final ObjectProvider<MyLogger> myLoggerProvider; ObjectProvider사용하기
    private final MyLogger myLogger; // 프록시 사용하기

    // ObjectProvider 덕분에 ObjectProvider.getObject 호출 시점까지 request scope 빈 생성 지연 가능
    public void logic(String id) {
        // MyLogger myLogger = myLoggerProvider.getObject(); ObjectProvider사용하기
        myLogger.log("service id = " + id);
    }
}
