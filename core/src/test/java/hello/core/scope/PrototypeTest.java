package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        //init 각각 호출됨 -> 서로 다른 인스턴스
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean test1 = ac.getBean(PrototypeBean.class);
        PrototypeBean test2 = ac.getBean(PrototypeBean.class);
        System.out.println("test1 = " + test1);
        System.out.println("test2 = " + test2);
        Assertions.assertThat(test1).isNotEqualTo(test2);
        Assertions.assertThat(test1).isNotSameAs(test2);
        //close 호출해도 destroy 메서드가 호출되지 않음
        ac.close();

        //직접 호출하기
        test1.destroy();
        test2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
