package study.spring_jpa.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {

  private Long itemId;

  private Long memberId;

  private int count;
}
