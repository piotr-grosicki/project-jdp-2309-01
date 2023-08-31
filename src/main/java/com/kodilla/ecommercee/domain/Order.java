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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  @Column(name = "ORDER_ID", unique = true)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  private Date orderDate;

  private String status;

  @ManyToMany(
          targetEntity =Product.class,
          mappedBy = "orders",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
  )
  @Builder.Default
  private List<Product> products = new ArrayList<>();

}

