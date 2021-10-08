package hello.core.beanFind_annotation;

//import hello.core.AppConfig;
//import hello.core.discount.DiscountPolicy;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

public class ApplicationContextBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 같은 타입이 둘 이상 있다면 중복오류가 발생한다")
    void findBeanByTypeDuplicate(){
        //발생하는 예외 : NoUniqueBeanDefinitionException
        //찾으려는 MemberRepository 객체를 반환하는 메서드가 2개이상이라서 스프링이 선택을 못함
//        MemberRepository bean = ac.getBean(MemberRepository.class);

        //예상한 예외가 발생하는지 검증
        org.junit.jupiter.api.Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있다면 -> 빈 이름을 지정하는 방법으로 가능하다")
    void findBeanByName(){
        //어떤 메서드를 조회하는지 지정하기
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
    
    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key : " + key+ " | value : " + beansOfType.get(key));
            System.out.println("beansOfType : "+ beansOfType);
            Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        }
    }

    //테스트용 클래스
    @Configuration
    static class SameBeanConfig{
        //같은 역할을 하는 메서드 생성
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
