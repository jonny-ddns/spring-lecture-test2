package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;


//로그 생성 및 출력하는 클래스
//스코프를 request 로 설정해서 http 요청마다 생성되고 요청이 종료되면 소멸된다
@Component
@Scope(value = "request")
public class MyLogger {
    private String uuid;    //식별번호
    private String requestURL;

    public MyLogger(String requestURL) {
        this.requestURL = requestURL;
    }

    //외부에서 요청된 url setter
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    //로그 출력 양식
    public void log(String message) {
        System.out.println("[" + uuid + "] (" + requestURL + ") " + message);
    }

    //생성 및 소멸 log
    //클래스가 생성되는 시점에 유일한 일련번호 생성하기
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        String message = ">> request scope bean created | " + this;
        log(message);
    }

    @PreDestroy
    public void close() {
        String message = ">> request scope bean destroyed | " + this;
        log(message);
    }
}
