package study.spring_jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("B")
@Getter
public class Book extends Item {

  private String author;

  private String isbn;

  public void update(String name, int price, int quantity, String author, String isbn) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.author = author;
    this.isbn = isbn;
  }
}
