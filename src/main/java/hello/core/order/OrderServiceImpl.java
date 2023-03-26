package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
// 회원 찾고 + 할인 정책 찾아야 하니까 해당 내용 주입하고
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); 바뀐 할인정책으로 써주면 된다.
    // 근데 여기서 보면 DiscoiuntPolicy 와 구체 클래스(FixDiscountPolicy, RateDiscountPolicy)에 의존하고 있다.
    // -> DIP 위반하고 있다.

    // 위의 코드도 DIP를 위반하지 않도록 바꿔주자
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하고 있고, 구체 클래스에 대해 의존하고 있지 않다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 관련 생성자 생성

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId); // 회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 의해 값 넘김

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}

