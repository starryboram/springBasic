package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    // MemberService memberService = new MemberServiceImpl();
    // OrderService orderService = new OrderServiceImpl();
    // AppConfig 사용을 위한 코드 변경 진행

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L; // primitive type는 null이 들어갈 수 있다.
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); // 넣어놔야 쓸 수 있으니까 조인으로 넣어 놓고

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        // import org.assertj.core.api.Assertions; 이걸 써야 assertThat테스트됨
    }
}
