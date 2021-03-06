package hello.core.lifeCycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class NetworkClient {
//public class NetworkClient implements InitializingBean , DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("NetworkClient - 생성자 호출 | url :  " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + " | message : " + message);
    }

    public void disconnect(){
        System.out.println("close : " + url);
    }

    //의존관계 주입이 끝나면 호출되는 메서드
//    @Override
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 test");
    }

    //빈이 종료될 때 호출도미
//    @Override
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
