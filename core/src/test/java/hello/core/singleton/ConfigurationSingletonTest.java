package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.memberService.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import hello.core.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //테스트를 위해 구현체를 가져옴
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository repository3 = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository repository1 = memberService.getMemberRepository();
        MemberRepository repository2 = orderService.getMemberRepository();

        Assertions.assertThat(repository1).isSameAs(repository2);
        Assertions.assertThat(repository1).isSameAs(repository3);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig 를 빈으로 가져오기
        //클래스 명 뒤에 다른 명칭이 붙는다
        //스프링이 임의의 다른 클래스를 생성함
        
        /*
        AppConfig 클래스를 상속받는 임의의 클래스 생성
        해당 인스턴스를 스프링 빈으로 등록하고 사용하게 함
        싱글톤 보장
         */
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean : "+ bean.getClass());
    }
}
