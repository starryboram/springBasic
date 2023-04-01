package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MemoryMemberRepository implements MemberRepository{
//  원래대로면 concurrentHashMap 써야한다. (동시성 이슈가 있기 떄문)
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member){
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId){
        return store.get(memberId);
    }
}
