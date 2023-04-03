package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 생성자 주입을 선택하게되면, 컴파일 시에 오류를 알 수 있고, final을 붙여서 사용이 가능하다.
// setter, 필드, 메서드 주입의 경우 모두 생성자 이후에 호출되기 때문에 필드에 final이라는 키워드 사용이 불가능하다.
// 오직 생성자 주입에서만 'final' 키워스 사용이 가능하다. 항상 생성자 주입을 써라. 가끕 옵션이 필요할 경우 setter 주입을 사용하라.
class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}