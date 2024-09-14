package study.spring_jpa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.spring_jpa.domain.item.Item;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager em;

  public void save(Item item) {
    if (item.getId() == null) {
      em.persist(item);
    } else {
      em.merge(item);
    }
  }

  public Item find(Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    String qlString = """
    SELECT
      i
    FROM
      Item i
    """;

    return em.createQuery(qlString, Item.class)
            .getResultList();
  }
}
