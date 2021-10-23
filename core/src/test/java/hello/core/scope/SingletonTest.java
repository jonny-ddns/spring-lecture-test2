package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    void singletonTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean test1 = ac.getBean(SingletonBean.class);
        SingletonBean test2 = ac.getBean(SingletonBean.class);
        System.out.println("test1 = " + test1);
        System.out.println("test2 = " + test2);
        Assertions.assertThat(test1).isEqualTo(test2);
        Assertions.assertThat(test1).isSameAs(test2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
