package study.spring_jpa.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring_jpa.domain.Address;
import study.spring_jpa.domain.Member;
import study.spring_jpa.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;

  @PostMapping("/api/v1/members")
  public CreateResponse createV1(@RequestBody @Valid Member member) {
    Long memberId = memberService.save(member.getName(), member.getAddress());

    return new CreateResponse(memberId);
  }

  @PostMapping("/api/v2/members")
  public CreateResponse createV2(@RequestBody @Valid CreateRequest request) {
    Long memberId = memberService.save(request.name, request.address);

    return new CreateResponse(memberId);
  }

  @GetMapping("/api/v1/members")
  public List<Member> listV1() {
    return memberService.findAll();
  }

  @GetMapping("/api/v2/members")
  public ListResponse listV2() {
    List<Member> members = memberService.findAll();
    List<Dto> dtoList = members.stream()
                              .map(member -> new Dto(member.getName()))
                              .toList();

    return new ListResponse(dtoList.size(), dtoList);
  }

  @Data
  static class CreateRequest {

    @NotEmpty
    private String name;

    private Address address;
  }

  @Data
  @AllArgsConstructor
  static class CreateResponse {

    private Long id;
  }

  @Data
  @AllArgsConstructor
  static class ListResponse<T> {

    private int count;

    private T data;
  }

  @Data
  @AllArgsConstructor
  static class Dto {
    private String name;
  }
}
