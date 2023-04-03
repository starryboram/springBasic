package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 조회빈이 2개 이상일 때 어떤 문제가 생기는지 보기 위해 @component 추가
//NoUniqueBeanDefinitionException 에러 발생 : 하위 타입으로 생성자의 파라미터를 쓸 수 있찌만, 이건 DIP를 위배하고 유연성이 떨어지게 됨. => 해결해보자.

/*
<조회 대상 빈이 2개일 때 해결하는 방법>

1. @Autowired 필드명 매칭
 @autowired는 처음에 타입 매칭을 시도하고, 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 일므을 추가 매칭한다.

2. @Quilifier -> @Quilifier끼리 매칭 -> 빈 이름 매칭
 추가 구분자를 붙여주는 방법이다. 빈 이름을 변경하는 것이 아니라 얘는 그냥 주입할 때 추가적인 방법이라고 생각하면 된다.

3. @Primary 사용
자주 사용하는 방법. 우선순위를 정하는 방법으로 @Autowired 시에 여러 빈이 매칭되면 @Primary가 우선권을 가지게 된다.

참고) @Primary보다 @Quilifier가 우선순위가 더 높다.
* */

// @Qualifier("fixDsicountPolicy") 특정 빈 조회하기
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){// enum type은 == 쓰는게 맞다.
            return discountFixAmount;
        } else{
            return 0;
        }
    }
}
