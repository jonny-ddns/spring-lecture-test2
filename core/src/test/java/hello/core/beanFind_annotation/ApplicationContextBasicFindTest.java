package hello.core.beanFind_annotation;

import hello.core.AppConfig;
import hello.core.memberService.MemberService;
import hello.core.memberService.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    //memberService 라는 메서드로 생성되는 MemberService 객체 가져오기
    //구현객체가 맞는지 확인하기
    @Test
    @DisplayName("빈 이름과 객체 타입으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService : "+ memberService);
//        System.out.println("memberService.getClass() : "+ memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름과 객체 타입(구현객체)으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService= ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회되지 안되는 경우 예외 잘 발생하는지")
    void findBeanByNameFail(){
        //No bean named 'test' available
//        ac.getBean("test", MemberServiceImpl.class);
//        MemberService memberService= ac.getBean("test", MemberService.class);

        //예외 확인코드
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("test", MemberService.class));
    }
}
