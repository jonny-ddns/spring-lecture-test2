package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.memberService.MemberService;
//import hello.core.memberService.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//Member 설정 테스트
public class MemberApp {
    void execute() {
        /*기존코드 - 순수 자바코드*/
        //AppConfig 를 통한 의존관계 주입
//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//
//        Member member1 = new Member(1L, "memberA", Grade.BASIC);
//        memberService.join(member1);
//
//        Member member2 = memberService.findMember(1L);
//        System.out.println("member1 : "+ member1.getName());
//        System.out.println("member2 : "+ member2.getName());
//        System.out.println("동일한 객체인가 : "+ member1.getName().equals(member2.getName()));

        /*스프링으로 코딩*/
        /*
        ApplicationContext ; 스프링 컨테이너(인터페이스)
        애노테이션 설정한 config 객체(AppConfig)파일을 스프링 객체로 얻어오기

        Bean 설정한 모든 객체가 스프링 컨테이너에 등록된다(관리된다)
         */
        
        //애노테이션 붙은 객체를 ApplicationContext 에서 관리하도록 지정
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //AppConfig 에서 등록한 빈
        //메서드 명을 name 으로 지정한다
        //리턴결과 인터페이스의 구현객체
        //1) name : 찾을 객체
        //2) requiredType : 객체 타입
//        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member1 = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(member1);

        Member member2 = memberService.findMember(1L);
        System.out.println("member1 : " + member1.getName());
        System.out.println("member2 : " + member2.getName());
        System.out.println("동일한 객체인가 : " + member1.getName().equals(member2.getName()));
    }
}

