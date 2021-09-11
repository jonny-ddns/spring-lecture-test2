package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void StatefulServiceTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        statefulService1.order1("userA", 10000);
        statefulService2.order1("userB", 20000);

        int price = statefulService1.getPrice();
        System.out.println("price : "+ price);

        Assertions.assertThat(price).isNotEqualTo(10000);
    }

    @Test
    void StatefulServiceTest2(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        int price;
        price = statefulService1.order2("userA", 10000);
        System.out.println("price : "+ price);

        price = statefulService2.order2("userB", 20000);
        System.out.println("price : "+ price);
    }

    //테스트를 전용 클래스
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
