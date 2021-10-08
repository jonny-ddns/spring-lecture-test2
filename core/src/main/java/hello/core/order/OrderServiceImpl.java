package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.repository.MemberRepository;
import org.springframework.stereotype.Component;
//import hello.core.repository.MemoryMemberRepository;

/*
정적인 의존관계
    --> 애플리케이션을 실행하지 않아도 관계를 분석할 수 있는것
    OrderServiceImpl 클래스를 보면 의존관계를 알 수 있다
    -OrderService 클래스를 구현한다
    -생성시 MemberRepository, DiscountPolicy 구현클래스를 주입받는다

동적인 의존관계
    -주입되는 코드만으로는 MemberRepository, DiscountPolicy 구현클래스가 주입되는지
    전혀 알 수가 없다
    -실제로 실행을 해봐야 알 수 있다

의존관계주입
    애플리케이션 실행시점에 실제 구현객체를 생성하고 클라이언트에 전달
    클라이언트와 서버의 의존관계가 성립되는것

.의존관계 주입을 사용하면
    정적인 의존관계 주입을 변경하지 않고
    동적인 의존관계 주입만 변경할 수 있다
    즉 정적코드는 정적으로, 동적코드는 동적으로 두게 된다
 */
/*참고로 화살표 방향으로 의존한다는 의미*/

@Component
public class OrderServiceImpl implements OrderService{
    //new 로 직접 생성하면 DiscountPolicy 인터페이스 뿐만 아니라 구현체도 함께 코딩하게 된다
    //코드가 작성된다 --> 코딩시 유지보수 대상 = 수정작업 필요함
    //이는 마치 배우가 대본을 선택하는 것과 같다
    //구현체에 의존한다 --> DIP 위반
    //클라이언트에 해당하는 OrderServiceImpl 코드도 수정해야함 --> OCP 원칙 위반
    //해결책 ; 별도의 설정클래스(AppConfig)를 마련해 구현객체를 생성하고 연결하는 책임을 부여한다
    //역할이 분리되어 각각의 책임에 집중할 수 있다
//    private MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    //생성자 주입 방식
    //MemberRepository, DiscountPolicy 객체 주입할 수 있는 코드
    //결과 ; 구현객체에 대한 코드가 사라진다. 외부 설정파일에서 역할 수행하기 때문
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 여부 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
