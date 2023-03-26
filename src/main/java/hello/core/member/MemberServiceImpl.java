package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //원래는 MemoryMemberRepository를 MemberServiceImple이 지정했었음. (배우가 -> 담당자를 섭외하는 것과 같다)
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); 바꿔주자
    private final MemberRepository memberRepository; // MemoryRepository에 대한 구현 로직 아예 없음. 인터페이스만 존재
    // 역할과 책임을 분리하기 위해, 생성자를 써준다.
    public MemberServiceImpl(MemberRepository memberRepository){
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
}
