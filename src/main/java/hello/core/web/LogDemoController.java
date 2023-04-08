package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // private final ObjectProvider<MyLogger> myLoggerProvider; // myLogger을 찾을 수 있는 dependency가 주입됨(ObjectProvider 이용하기)
    private final MyLogger myLogger; // 프록시 사용하기(가짜 객체를 미리 만들어둔다)
    // 가짜이기 때문에 그냥 얘를 공유해도 상관이 없다. 어디든 주입해도 상관이 없다. 싱글톤처럼 동작하게한다.
    // Provider를 사용하든, 프록시를 사용하든 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다.

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();// 어떤 URL 요청이 들어왔는지 확인하기 위한 용도
       // MyLogger myLogger = myLoggerProvider.getObject(); ObjectProvider사용하기
        System.out.println("mylogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL); // 진짜로 만들어준다.

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "ok";
    }
}
