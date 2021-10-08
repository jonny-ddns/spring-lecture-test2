package hello.core.repository;

import hello.core.member.Member;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

//컴포넌트 스캔설정 -> 자동으로 컴포넌트로 등록하기(AutoAppConfig)
@Component
public class MemoryMemberRepository implements MemberRepository{
    private static final Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
