package study.spring_jpa.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_jpa.domain.Order;
import study.spring_jpa.repository.OrderDto;
import study.spring_jpa.repository.OrderRepository;
import study.spring_jpa.repository.OrderSearch;

import java.util.List;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order → Member
 * Order → Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderApiController {

  private final OrderRepository orderRepository;

  @GetMapping("/api/v1/orders")
  public List<Order> listV1() {
    List<Order> orders = orderRepository.findAll(new OrderSearch());

    for (Order order : orders) {
      order.getMember().getName();
      order.getDelivery().getAddress();
    }

    return orders;
  }

  @GetMapping("/api/v2/orders")
  public List<OrderDto> listV2() {
    List<Order> orders = orderRepository.findAll(new OrderSearch());
    List<OrderDto> dtoList = orders.stream()
                              .map(OrderDto::new)
                              .toList();
    return dtoList;
  }

  @GetMapping("/api/v3/orders")
  public List<OrderDto> listV3() {
    List<Order> orders = orderRepository.findAll();
    List<OrderDto> dtoList = orders.stream()
                              .map(OrderDto::new)
                              .toList();
    return dtoList;
  }

  @GetMapping("/api/v4/orders")
  public List<OrderDto> listV4() {
    List<OrderDto> orders = orderRepository.findAllByDto();

    return orders;
  }
}
