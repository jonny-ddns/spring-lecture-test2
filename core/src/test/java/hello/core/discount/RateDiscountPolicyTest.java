package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP 는 10% 할인 적용되어야 한다  ")
    void discount_vip(){
        //given
        Member member = new Member(1L, "홍길동", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 20000);
        //then
        Assertions.assertThat(discount).isEqualTo(2000);
    }
    
    @Test
    @DisplayName("VIP 가 아닌 아닌경우 할인 적용되지 않을 것")
     void discount_basic(){
        Member member = new Member(1L, "홍길동", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);
        //0 을 기대했는데 1000이 나온다
//        Assertions.assertThat(discount).isEqualTo(1000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}