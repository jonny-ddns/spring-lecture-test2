package hello.core.singleton;

//싱글톤의 문제를 보여주는 예시
public class StatefulService {
    private int price;  //상태를 유지하는 필드 --> 객체가 공유되면서 문제가 발생함 
    public void order1(String name, int price){
        System.out.println("name : "+ name + " | price : "+ price);
        this.price = price;
    }

    //상태를 유지하지 않도록(무상태) 코딩
    //전역변수 대신 지역변수를 사용하도록 코딩
    public int order2(String name, int price){
        System.out.println("name : "+ name + " | price : "+ price);
        return price;
    }

    public int getPrice() {
        return price;
    }
}
