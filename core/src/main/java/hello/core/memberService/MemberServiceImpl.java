package hello.core.memberService;

import hello.core.member.Member;
import hello.core.repository.MemberRepository;

public class MemberServiceImpl implements MemberService{
    //추상화와 구체화 둘다 의존하고 있다 -> 안좋은 코딩 방식. DI위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

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
