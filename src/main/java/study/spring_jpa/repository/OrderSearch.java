package study.spring_jpa.repository;

import lombok.Getter;
import lombok.Setter;
import study.spring_jpa.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

  private String memberName;

  private OrderStatus orderStatus;
}
