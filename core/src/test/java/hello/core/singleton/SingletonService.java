package hello.core.singleton;

//싱글톤 참고
public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }
    
    public void methodA(){
        System.out.println("싱글톤 메서드 호출");
    }
}
