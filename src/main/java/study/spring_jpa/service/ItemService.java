package study.spring_jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring_jpa.domain.item.Item;
import study.spring_jpa.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public void save(Item item) {
    itemRepository.save(item);
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
