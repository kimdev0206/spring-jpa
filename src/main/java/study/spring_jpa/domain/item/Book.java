package study.spring_jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item {

  private String author;

  private String isbn;

  public void update(String name, int price, int quantity, String author, String isbn) {
    this.setName(name);
    this.setPrice(price);
    this.setQuantity(quantity);
    this.setAuthor(author);
    this.setIsbn(isbn);
  }
}
