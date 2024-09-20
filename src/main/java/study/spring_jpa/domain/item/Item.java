package study.spring_jpa.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import study.spring_jpa.domain.Category;
import study.spring_jpa.exception.NotEnoughQuantityException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
@Getter
public abstract class Item {

  @Id @GeneratedValue
  @Column(name = "item_id")
  private Long id;

  protected String name;

  protected int price;

  protected int quantity;

  @ManyToMany(mappedBy = "items")
  private List<Category> categories = new ArrayList<>();

  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }

  public void removeQuantity(int quantity) {
    if(this.quantity - quantity < 0) {
      throw new NotEnoughQuantityException("재고가 부족합니다.");
    }

    this.quantity -= quantity;
  }
}
