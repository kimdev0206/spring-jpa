package study.spring_jpa;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.spring_jpa.domain.*;
import study.spring_jpa.domain.item.Book;

@Component
@RequiredArgsConstructor
public class InitDatabase {

  private final Service service;

  @PostConstruct
  public void init() {
    service.initA();
    service.initB();
  }

  @Component
  @RequiredArgsConstructor
  static class Service {

    private final EntityManager em;

    @Transactional
    public void initA() {
      Member member = new Member();
      member.update("memberA", new Address("주소", "거리", "우편번호"));
      em.persist(member);

      Book bookA = new Book();
      bookA.update("bookA", 10_000, 100, "저자명", "isbn");
      em.persist(bookA);

      Book bookB = new Book();
      bookB.update("bookB", 20_000, 100, "저자명", "isbn");
      em.persist(bookB);

      OrderItem orderItemA = OrderItem.create(bookA, 10_000, 1);
      OrderItem orderItemB = OrderItem.create(bookB, 20_000, 2);

      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());

      Order order = Order.create(member, delivery, orderItemA, orderItemB);
      em.persist(order);
    }

    @Transactional
    public void initB() {
      Member member = new Member();
      member.update("memberB", new Address("주소", "거리", "우편번호"));
      em.persist(member);

      Book bookC = new Book();
      bookC.update("bookC", 10_000, 100, "저자명", "isbn");
      em.persist(bookC);

      Book bookD = new Book();
      bookD.update("bookD", 20_000, 100, "저자명", "isbn");
      em.persist(bookD);

      OrderItem orderItemC = OrderItem.create(bookC, 10_000, 1);
      OrderItem orderItemD = OrderItem.create(bookD, 20_000, 2);

      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());

      Order order = Order.create(member, delivery, orderItemC, orderItemD);
      em.persist(order);
    }
  }
}
