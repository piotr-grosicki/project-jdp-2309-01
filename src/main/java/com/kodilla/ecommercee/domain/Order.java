package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private Long id;

  @ManyToOne
  private User user;

  private Date orderDate;

  private String status;

  /*
  Po zrobieniu Product Entity
  @OneToMany(
          targetEntity =Product.class,
          mappedBy = "order",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  private List<Product> products = new ArrayList<>();
}
   */
}