package study.spring_jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring_jpa.domain.item.Book;
import study.spring_jpa.domain.item.Item;
import study.spring_jpa.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public Item save(String name, int price, int quantity, String author, String isbn) {
    Book item = new Book();
    item.update(name, price, quantity, author, isbn);

    itemRepository.save(item);

    return item;
  }

  @Transactional
  public void update(Long itemId, String name, int price, int quantity, String author, String isbn) {
    Book findItem = (Book) itemRepository.find(itemId);
    findItem.update(name, price, quantity, author, isbn);
  }

  @Transactional(readOnly = true)
  public Item find(Long itemId) {
    return itemRepository.find(itemId);
  }

  @Transactional(readOnly = true)
  public List<Item> findAll() {
    return itemRepository.findAll();
  }

}
