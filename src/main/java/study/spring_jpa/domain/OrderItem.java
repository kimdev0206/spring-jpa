package study.spring_jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.spring_jpa.domain.item.Item;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

  @Id @GeneratedValue
  @Column(name = "order_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name = "order_id")
  @Setter(AccessLevel.PROTECTED)
  private Order order;

  private int price;

  private int count;

  public void cancel() {
    this.item.addQuantity(this.count);
  }

  // 생성 메서드 //
  public static OrderItem create(Item item, int price, int count) {
    OrderItem orderItem = new OrderItem();
    orderItem.item = item;
    orderItem.price = price;
    orderItem.count = count;

    item.removeQuantity(count);

    return orderItem;
  }

  // 테스트 메서드 //
  public int getTotalPrice() {
    return this.price * this.count;
  }
}
