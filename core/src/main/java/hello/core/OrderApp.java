package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.memberService.MemberService;
//import hello.core.memberService.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        //기존 코드
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();
        //AppConfig 객체 가져와서 구현객체 얻기
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

//        long memberId = 1L;
//        Member member = new Member(memberId, "홍길동", Grade.VIP);
//        memberService.join(member);
//
//        Order order = orderService.createOrder(memberId, "itemA", 10000);
//        System.out.println(order);

        /* 스프링 적용 코드 */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId, "홍길동", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println(order);
    }
}
