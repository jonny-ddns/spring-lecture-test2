package hello.core.discount;

import hello.core.member.Member;

//할인정책 인터페이스
//리턴; 할인된 금액
public interface DiscountPolicy {
    int discount(Member member, int price);
}
