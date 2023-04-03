package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Qualifier("mainDiscountPolicy") 특정 빈 조회하기
@Primary // 여러 개의 빈이 있을 때 RateDiscountPolicy가 우선권을 갖게 하고 싶을 때 @Primary 넣어서 사용해주면 된다.
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10; // 10프로 할인해주는 로직 작성
// ctrl + shift + T 누르면 자동으로 test 생성
    @Override
    public int discount(Member member, int price){
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent/100;
        } else{
            return 0;
        }
    }
}
