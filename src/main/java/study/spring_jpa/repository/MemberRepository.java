package study.spring_jpa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.spring_jpa.domain.Member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

  private final EntityManager em;

  public void save(Member member) {
    em.persist(member);
  }

  public Member find(Long id) {
    return em.find(Member.class, id);
  }

  public List<Member> findAll() {
    String qlString = """
    SELECT
      m
    FROM
      Member m
    """;

    return em.createQuery(qlString, Member.class)
            .getResultList();
  }

  public List<Member> findByName(String name) {
    String qlString = """
    SELECT
      m
    FROM
      Member m
    WHERE
      m.name = :name
    """;

    return em.createQuery(qlString, Member.class)
            .setParameter("name", name)
            .getResultList();
    }
}
