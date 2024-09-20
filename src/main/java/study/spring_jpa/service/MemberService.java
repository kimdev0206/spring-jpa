package study.spring_jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring_jpa.domain.Address;
import study.spring_jpa.domain.Member;
import study.spring_jpa.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long save(String name, Address address) {
    Member member = new Member();
    member.update(name, address);

    validateDuplicateMember(member.getName());
    memberRepository.save(member);

    return member.getId();
  }

  private void validateDuplicateMember(String name) {
    List<Member> findMembers = memberRepository.findByName(name);

    if (!findMembers.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  @Transactional(readOnly = true)
  public Member find(Long memberId) {
    return memberRepository.find(memberId);
  }

  @Transactional(readOnly = true)
  public List<Member> findAll() {
    return memberRepository.findAll();
  }
}
