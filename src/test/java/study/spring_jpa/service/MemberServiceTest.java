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
    String name = "member";
    String city = "도시";
    String street = "거리";
    String zipcode = "우편번호";

    // when
    Long savedId = memberService.save(name, city, street, zipcode);

    // then
    Member member = memberRepository.find(savedId);
    assertThat(member.getId()).isEqualTo(savedId);
  }

  @Test(expected = IllegalStateException.class)
  public void 회원가입_중복회원() {
    // given
    String name = "member";
    String city = "도시";
    String street = "거리";
    String zipcode = "우편번호";

    // when
    memberService.save(name, city, street, zipcode);
    memberService.save(name, city, street, zipcode);

    // then
    fail("중복 회원 예외가 발생해야 합니다.");
  }
}