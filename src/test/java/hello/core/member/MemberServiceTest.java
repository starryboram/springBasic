package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //필드 필요(추상화와 구체화를 의존함)
   // MemberService memberService = new MemberServiceImpl(); AppConfig 이용해서 하게끔 코드 바꿔주기

    MemberService memberService;
    @BeforeEach // 테스트 실행되기 전에 무조건 실행되게 하는 것 어노테이션 넣어주고
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L); // 2L로 하면 실패

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
