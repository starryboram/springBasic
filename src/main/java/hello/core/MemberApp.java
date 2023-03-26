package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //psvm치고 enter 누르면 바로 만들어짐
    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl(); appConfig로부터 서비스 생성

        // 관심사 분리
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // spring으로의 전환
        // Appconfig.class를 가져오므로써, 관련 설정정보를 다 가져온다.(@Bean 붙은걸 다 가져온다)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member); // 회원가입 되어야지

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
