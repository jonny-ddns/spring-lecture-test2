package hello.core.beanFind_annotation;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//어떤 빈이 있는지 확인하기
class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        //모든 빈 가져오기
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name : "+ beanDefinitionName + " object : "+ bean);
        }
    }

    @Test
    @DisplayName("application 빈 출력하기")
    void findApplicationBean(){
        //애플리케이션 빈 가져오기
        //개발자가 직접 등록한 빈
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name : "+ beanDefinitionName + " object : "+ bean);
            }
        }
    }

    @Test
    @DisplayName("infrastructure 빈 출력하기")
    void findInfrastructureBean(){
        //인프라스트럭쳐 빈 가져오기
        //스프링이 내부에서 사용하는 빈
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name : "+ beanDefinitionName + " object : "+ bean);
            }
        }
    }
}
