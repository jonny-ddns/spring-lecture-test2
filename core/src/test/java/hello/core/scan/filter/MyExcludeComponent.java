package hello.core.scan.filter;

import java.lang.annotation.*;

//Target - 클래스 레벨에 붙도록 type 으로 설정
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyExcludeComponent {
}
