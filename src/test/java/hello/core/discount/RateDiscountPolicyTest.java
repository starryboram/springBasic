package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
// 내가 실행할 테스트를 가져오고
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test // test를 위한 어노테이션 생성(성공 테스트)
    @DisplayName("VIP는 10% 할인이 적용되어야 한다") // 보여지는 제목 설정
    void vip_o(){
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP); // 회원 1명이 주어짐
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        Assertions.assertThat(discount).isEqualTo(1000); // static import해주는 것이 좋다.
    }

    @Test //실패 테스트
    @DisplayName("VIP가 아니면 10% 할인이 적용되지 않아야 한다")
    void vip_x(){
        // given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        Assertions.assertThat(discount).isEqualTo(0); // 할인 금액은 0원이야 함.
    }
}