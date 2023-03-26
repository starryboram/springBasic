package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 전체를 관장하는 클래스를 하나 만들자.(전체 동작 방식을 구성하는 클래스)
public class AppConfig {
// 중복 코드 제거를 위해서 리팩터링 실행(윈도우: ctrl + alt + m)
    // 리팩터링 이유: 메서드명을 통해 어떤 일을 하는지 구체적으로 명확하게 보인다.
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
