package study.spring_jpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.spring_jpa.domain.Address;
import study.spring_jpa.domain.Member;
import study.spring_jpa.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/members/new")
  public String createForm(Model model) {
    model.addAttribute("memberForm", new MemberForm());

    return "members/createForm";
  }

  @PostMapping("/members/new")
  public String create(@Valid MemberForm form, BindingResult result) {

    if (result.hasErrors()) {
      return "members/createForm";
    }

    Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
    memberService.save(form.getName(), address);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findAll();
    model.addAttribute("members", members);

    return "members/list";
  }
}
