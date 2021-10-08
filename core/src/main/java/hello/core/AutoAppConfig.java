package hello.core;
//componentScan 방식으로 자동으로 컴포넌트 등록 및 의존관계 주입하는 클래스

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//컴포넌트 스캔과 의존관계 자동 주입 시작하기
//@ComponentScan --> @Component 애노테이션 붙은 클래스를 찾아 빈으로 등록함
@Configuration
@ComponentScan(
    //탐색할 패키지의 기본위치 설정
    //모든 자바코드, 라이브러리를 찾지 않도록 하기

    //만약 지정하지 않는 경우 디폴트는
    //ComponentScan 애노테이션이 부착된 클래스 및 하위클래스를 탐색하게 됨

    //권장
    //패키지 위치를 지정하지 않고
    //설정정보 클래스 위치를 최상단에 위치하기
    //그러면 자동으로 하위클래스는 탐색대상이다
    basePackages = "hello.core.member",
    basePackageClasses = AutoAppConfig.class,

    //제외할 항목 지정하기
    //AppConfig 수동으로 등록한 설정파일은 자동으로 처리되지 않도록 제외함
    //참고로 @Configuration 안에 들어가면 @Component 애노테이션이 붙어있어서 자동으로 처리됨
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    
}
