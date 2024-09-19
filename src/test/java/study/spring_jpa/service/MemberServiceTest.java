package study.spring_jpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import study.spring_jpa.domain.Member;
import study.spring_jpa.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  public void 회원가입() {
    // given
    Member member = new Member();
    member.setName("member");

    // when
    Long savedId = memberService.save(member);

    // then
    assertThat(memberRepository.find(savedId)).isEqualTo(member);
  }

  @Test(expected = IllegalStateException.class)
  public void 회원가입_중복회원() {
    // given
    Member memberA = new Member();
    memberA.setName("member");

    Member memberB = new Member();
    memberB.setName("member");

    // when
    memberService.save(memberA);
    memberService.save(memberB);

    // then
    fail("중복 회원 예외가 발생해야 합니다.");
  }
}