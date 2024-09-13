package study.spring_jpa1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.spring_jpa1.domain.item.Item;

@Entity
@Getter @Setter
public class OrderItem {

  @Id @GeneratedValue
  @Column(name = "order_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  private int price;

  private int count;
}
