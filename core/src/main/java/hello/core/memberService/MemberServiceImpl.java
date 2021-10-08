package hello.core.memberService;

//서비스 설정 클래스
import hello.core.member.Member;
import hello.core.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component ; AppConfig 말고 AutoAppConfig 에서 자동으로 관리하도록 하기
/*
@ComponentScan 애노테이션은 @Component 애노테이션이 붙은 빈을 모두 관리하는 애노테이션이다
그렇다면 이 과정은 관리할 빈을 등록, 즉 매핑하는 과정이다
그렇다면 의존관계(무엇이 무엇에 주입되는지 a랑 b중에 무엇이 주입대상이냐??) 주입은??

AppConfig 는 @Bean 으로 자바+스프링 코드를 사용해 수동으로 의존관계를 주입한다
-> 근데 자동으로 어떻게 빈을 스프링에 등록할 것인가?
-> @Autowired 애노테이션을 붙여서 자동으로 의존관계를 등록할 수 있다
-> @Autowired 애노테이션은 여러 군데 붙일 수 있지만 생성자에 붙이는 것이 가장 깔끔하다

일단 @Autowired 는 ac.getBean(....class) 역할을 수행한다고 보면된다
 */

//컴포넌트 이름 지정하기(보통 디폴트로 지정되는 이름을 사용함)
//@Component("[componentScanTest-MemberServiceImpl]")
@Component
public class MemberServiceImpl implements MemberService{
    //추상화와 구체화 둘다 의존하고 있다 -> 안좋은 코딩 방식. DI 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //싱글톤 여부 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
