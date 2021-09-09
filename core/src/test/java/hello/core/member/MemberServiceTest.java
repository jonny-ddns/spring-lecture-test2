package hello.core.member;

import hello.core.AppConfig;
import hello.core.memberService.MemberService;
import hello.core.memberService.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;
    //    MemberService memberService = new MemberServiceImpl();

    @BeforeEach
    void beforeEach(){
        memberService = new AppConfig().memberService();
    }

    @Test
    void join(){
        //given
        Member memberNew = new Member();

        //when
        memberService.join(memberNew);
        Member memberFind =  memberService.findMember(9999L);

        //then
        Assertions.assertThat(memberNew).isEqualTo(memberFind);
    }
}
