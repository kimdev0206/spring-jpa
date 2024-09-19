package study.spring_jpa.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

  private Long id;

  private String name;

  private int price;

  private int quantity;

  private String author;

  private String isbn;

  public static BookForm create(Long id, String name, int price, int quantity, String author, String isbn) {
    BookForm form = new BookForm();
    form.setId(id);
    form.setName(name);
    form.setPrice(price);
    form.setQuantity(quantity);
    form.setAuthor(author);
    form.setIsbn(isbn);

    return form;
  }
}
