package study.spring_jpa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import study.spring_jpa.domain.Address;
import study.spring_jpa.domain.Order;
import study.spring_jpa.domain.OrderStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDto {

  private Long id;

  private String name;

  private LocalDateTime orderDatetime;

  private OrderStatus status;

  private Address address;

  public OrderDto(Order order) {
    this.id = order.getId();
    this.name = order.getMember().getName();
    this.orderDatetime = order.getOrderDatetime();
    this.status = order.getStatus();
    this.address = order.getDelivery().getAddress();
  }
}