package study.spring_jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.spring_jpa.domain.Member;
import study.spring_jpa.domain.Order;
import study.spring_jpa.domain.item.Item;
import study.spring_jpa.repository.OrderSearch;
import study.spring_jpa.service.ItemService;
import study.spring_jpa.service.MemberService;
import study.spring_jpa.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

  private final ItemService itemService;

  private final MemberService memberService;

  private final OrderService orderService;

  @GetMapping("/orders/new")
  public String createForm(Model model) {
    List<Item> items = itemService.findAll();
    List<Member> members = memberService.findAll();

    model.addAttribute("items", items);
    model.addAttribute("members", members);
    model.addAttribute("orderForm", new OrderForm());

    return "orders/createForm";
  }

  @PostMapping("/orders/new")
  public String create(OrderForm form) {
    orderService.save(form.getMemberId(), form.getItemId(), form.getCount());

    return "redirect:/orders";
  }

  @GetMapping("/orders")
  public String list(OrderSearch orderSearch, Model model) {
    List<Order> orders = orderService.findAll(orderSearch);
    model.addAttribute("orders", orders);

    return "orders/list";
  }

  @PostMapping("/orders/{orderId}/cancel")
  public String cancel(@PathVariable("orderId") Long orderId) {
    orderService.cancel(orderId);

    return "redirect:/orders";
  }
}
