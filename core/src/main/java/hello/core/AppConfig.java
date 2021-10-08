package hello.core;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.memberService.MemberService;
import hello.core.memberService.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//DI container
//구현객체를 생성 및 연결하는 역할
//--> 애노테이션 기반 자바 설정 클래스
@Configuration
public class AppConfig {
    //생성할 구현객체 선택
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //MemberService 구현객체 주입하기
    
    //@bean -> 스프링 컨테이너에 등록하기
    //자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 같이 처리된다
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    //MemberRepository
    //OrderService 구현객체 주입하기
    @Bean   
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /*-------------------------*/
    //메서드를 통한 구현체 선택
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    //메서드를 통한 구현체 선택
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
