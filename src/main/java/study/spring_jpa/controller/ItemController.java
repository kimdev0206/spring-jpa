package study.spring_jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.spring_jpa.domain.item.Book;
import study.spring_jpa.domain.item.Item;
import study.spring_jpa.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/items/new")
  public String createForm(Model model) {
    model.addAttribute("itemForm", new BookForm());

    return "items/createForm";
  }

  @PostMapping("/items/new")
  public String create(BookForm form) {
    itemService.save(form.getName(), form.getPrice(), form.getQuantity(), form.getAuthor(), form.getIsbn());

    return "redirect:/";
  }

  @GetMapping("/items")
  public String list(Model model) {
    List<Item> items = itemService.findAll();
    model.addAttribute("items", items);

    return "items/list";
  }

  @GetMapping("/items/{itemId}/edit")
  public String updateForm(@PathVariable("itemId") Long itemId, Model model) {
    Book item = (Book) itemService.find(itemId);
    BookForm form = BookForm.create(item.getId(), item.getName(), item.getPrice(), item.getQuantity(), item.getAuthor(), item.getIsbn());

    model.addAttribute("itemForm", form);

    return "items/updateForm";
  }

  @PostMapping("/items/{itemId}/edit")
  public String update(BookForm form) {
    itemService.update(form.getId(), form.getName(), form.getPrice(), form.getQuantity(), form.getAuthor(), form.getIsbn());

    return "redirect:/items";
  }
}
