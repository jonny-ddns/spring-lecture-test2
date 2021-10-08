package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.memberService.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        //ComponentScan 및 Autowired 의존관계 주입 확인
        /*
        출력 로그를 확인하면
        아래 항목 로그를 통해 자동으로 컴포넌트 스캔 및 자동등록을
        싱글톤을 정상 처리했음을 알 수 있음
        ... ClassPathBeanDefinitionScanner...
        ... Autowiring ...
         */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
