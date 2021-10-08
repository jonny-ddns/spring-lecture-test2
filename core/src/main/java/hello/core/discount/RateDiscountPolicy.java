package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

//10퍼센트 할인해주는 로직
//컴포넌트 관리되도록 애노테이션 등록
@Component
public class RateDiscountPolicy implements DiscountPolicy {
    //테스트 만들기 단축키 : ctrl + shift + T
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            int discountPercent = 10;
            return price * discountPercent / 100;
        }
        return 0;
    }
}
