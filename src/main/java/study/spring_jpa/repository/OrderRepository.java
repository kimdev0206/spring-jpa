package study.spring_jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.spring_jpa.domain.Member;
import study.spring_jpa.domain.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order) {
    em.persist(order);
  }

  public Order find(Long id) {
    return em.find(Order.class, id);
  }

  public List<Order> findAll(OrderSearch orderSearch) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Order> cq = cb.createQuery(Order.class);
    Root<Order> order = cq.from(Order.class);
    Join<Order, Member> member = order.join("member", JoinType.INNER);

    List<Predicate> criteria = new ArrayList<>();

    if (orderSearch.getOrderStatus() != null) {
      Predicate status = cb.equal(order.get("status"), orderSearch.getOrderStatus());
      criteria.add(status);
    }

    if (StringUtils.hasText(orderSearch.getMemberName())) {
      Predicate memberName = cb.like(member.get("name"), "%" + orderSearch.getMemberName() + "%");
      criteria.add(memberName);
    }

    Predicate[] predicates = criteria.toArray(new Predicate[criteria.size()]);
    cq.where(cb.and(predicates));

    TypedQuery<Order> tq = em.createQuery(cq).setMaxResults(1000);
    return tq.getResultList();
  }

  public List<Order> findAll() {
    String qlString = """
    SELECT
      o
    FROM
      Order o
    JOIN FETCH
      o.member m
    JOIN FETCH
      o.delivery d
    """;

    return em.createQuery(qlString, Order.class)
            .getResultList();
  }

  public List<OrderDto> findAllByDto() {
    String qlString = """
    SELECT
      new study.spring_jpa.repository.OrderDto(
        o.id,
        m.name,
        o.orderDatetime,
        o.status,
        d.address
      )
    FROM
      Order o
    JOIN
      o.member m
    JOIN
      o.delivery d
    """;

    return em.createQuery(qlString, OrderDto.class)
             .getResultList();
  }
}
