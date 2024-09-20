package study.spring_jpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import study.spring_jpa.domain.Order;
import study.spring_jpa.domain.OrderStatus;
import study.spring_jpa.domain.item.Item;
import study.spring_jpa.exception.NotEnoughQuantityException;
import study.spring_jpa.repository.OrderRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

  @Autowired ItemService itemService;

  @Autowired MemberService memberService;

  @Autowired OrderService orderService;

  @Autowired OrderRepository orderRepository;

  @Test
  public void 상품주문() {
    // given
    Long memberId = createMember();
    Item book = createBook();
    int orderCount = 2;

    // when
    Long orderId = orderService.save(memberId, book.getId(), orderCount);
    Order order = orderRepository.find(orderId);

    // then
    assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);
    assertThat(order.getOrderItems().size()).isEqualTo(1);
    assertThat(order.getTotalPrice()).isEqualTo(10_000 * orderCount);
    assertThat(book.getQuantity()).isEqualTo(10 - orderCount);
  }

  @Test(expected = NotEnoughQuantityException.class)
  public void 상품주문_재고수량부족() {
    // given
    Long memberId = createMember();
    Item book = createBook();
    int orderCount = 11;

    // when
    orderService.save(memberId, book.getId(), orderCount);

    // then
    fail("재고 수량 부족 예외가 발생해야 합니다.");
  }

  @Test
  public void 주문취소() {
    // given
    Long memberId = createMember();
    Item book = createBook();
    int orderCount = 2;

    Long orderId = orderService.save(memberId, book.getId(), orderCount);

    // when
    orderService.cancel(orderId);
    Order order = orderRepository.find(orderId);

    // then
    assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    assertThat(book.getQuantity()).isEqualTo(10);
  }

  private Long createMember() {
    String name = "member";
    String city = "도시";
    String street = "거리";
    String zipcode = "우편번호";

    Long memberId = memberService.save(name, city, street, zipcode);
    return memberId;
  }

  private Item createBook() {
    String name = "book";
    int price = 10_000;
    int quantity = 10;
    String author = "저자명";
    String isbn = "isbn";

    Item item = itemService.save(name, price, quantity, author, isbn);
    return item;
  }
}