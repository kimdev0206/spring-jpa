package study.spring_jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring_jpa.domain.Delivery;
import study.spring_jpa.domain.Member;
import study.spring_jpa.domain.Order;
import study.spring_jpa.domain.OrderItem;
import study.spring_jpa.domain.item.Item;
import study.spring_jpa.repository.ItemRepository;
import study.spring_jpa.repository.MemberRepository;
import study.spring_jpa.repository.OrderRepository;
import study.spring_jpa.repository.OrderSearch;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;
  private final OrderRepository orderRepository;

  @Transactional
  public Long save(Long memberId, Long itemId, int count) {
    Member member = memberRepository.find(memberId);
    Item item = itemRepository.find(itemId);

    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());
    OrderItem orderItem = OrderItem.create(item, item.getPrice(), count);
    Order order = Order.create(member, delivery, orderItem);

    orderRepository.save(order);

    return order.getId();
  }

  @Transactional
  public void cancel(Long orderId) {
    Order order = orderRepository.find(orderId);

    order.cancel();
  }

  @Transactional
  public List<Order> findAll(OrderSearch orderSearch) {
    return orderRepository.findAll(orderSearch);
  }
}
