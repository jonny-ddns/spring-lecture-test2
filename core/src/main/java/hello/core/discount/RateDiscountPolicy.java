package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//10퍼센트 할인해주는 로직
public class RateDiscountPolicy implements DiscountPolicy {
    private final int discountPercent = 10;
    /*
    테스트 만들기
    ctrl + shift + T
     */
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        return 0;
    }
}
