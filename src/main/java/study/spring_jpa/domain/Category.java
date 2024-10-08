package study.spring_jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import study.spring_jpa.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

  @Id @GeneratedValue
  @Column(name = "category_id")
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(
    name = "category_item",
    joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id")
  )
  private final List<Item> items = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private final List<Category> children = new ArrayList<>();

  // 연관관계 메서드 //
  public void addChildCategory(Category child) {
    this.children.add(child);
    child.parent = this;
  }
}
