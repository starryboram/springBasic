package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
// 회원 찾고 + 할인 정책 찾아야 하니까 해당 내용 주입하고
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); 바뀐 할인정책으로 써주면 된다.
    // 근데 여기서 보면 DiscoiuntPolicy 와 구체 클래스(FixDiscountPolicy, RateDiscountPolicy)에 의존하고 있다.
    // -> DIP 위반하고 있다.

    // 위의 코드도 DIP를 위반하지 않도록 바꿔주자
    private final MemberRepository memberRepository; // final을 붙인다 => 값이 무조건 있어야 한다.
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하고 있고, 구체 클래스에 대해 의존하고 있지 않다.

    @Autowired // 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository; // final 붙인 애가없으면 => 컴파일 오류난다.
        this.discountPolicy = discountPolicy;
    }

    /*
    생성자 주입: 생성자를 통해서 의존관계를 주입받는다.
    생성자 호출 시점에 딱 1번만 호출되는 것이 보장되기 때문에 "불변, 필수" 의존 관계에서 사용한다.
    생성자가 1개만 있다면, @Autowired는 생략이 가능하다.

    setter 주입: final 제거하고 setMemberRepository~ setDiscountPolicy~ 이렇게 쓰는 방식
    선택, 변경 가능성이 있는 애들한테 사용한다. MemberRepository가 등록이 되지 않았어도 사용이 가능하다.

    필드 주입: 코드가 간결하지만, 외부에서 변경이 불가능해서 테스트하기 힘들다.
    @Autowired private final MemberRepository memberRepository; 이렇게 사용하면 된다.
    DI 프레임워크가 없으면 아무것도 할 수 없다. 사용하지 말라고 권고한다.
    테스트할때 가짜 memberRepository를 만들고 싶은데, 안됨. => 못만들어서 테스트하기 힘듦.
    애플리케이션의 실제 코드와 관계 없는 테스트 코드나 스프링설정을 목적으로 하는 @Configuration같은 곳에서만 특수 목적으로 사용

    일반 메서드에 주입: 한 번에 여러 필드를 주입 받을 수 있다. 잘 사용 안함.
    @Autowired 생성자 주입하듯이 쓰면 됨.
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;}

    @Autowired의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 주입할 대상이 없어도 동작하게 하려면
    @Autowired(required = false)로 지정하면 된다.
    */

    // 관련 생성자 생성
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId); // 회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 의해 값 넘김

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

