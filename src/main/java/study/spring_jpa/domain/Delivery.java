package study.spring_jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Delivery {

  @Id @GeneratedValue
  @Column(name = "delivery_id")
  private Long id;

  @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
  @JsonIgnore
  @Setter(AccessLevel.PROTECTED)
  private Order order;

  @Embedded
  @Setter
  private Address address;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status;
}
