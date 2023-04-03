package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// 특정 빈 조회하기
// 방법2. @Qualifier("mainDiscountPolicy") 특정 빈 조회하기
// 방법3.여러 개의 빈이 있을 때 RateDiscountPolicy가 우선권을 갖게 하고 싶을 때 @Primary 넣어서 사용해주면 된다.
@Primary
// 변형 @MainDiscountPolicy 직접 만든 어노테이션으로 컴파일 시 오류를 발견하게 하기
// 어노테이션은 상속이라는 개념이 없다. 여러 어노테이션을 모아서 사용하는 기능은 스프링이 지원해주는 기능임.
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
