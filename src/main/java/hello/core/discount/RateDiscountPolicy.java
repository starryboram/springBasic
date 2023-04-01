package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
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
