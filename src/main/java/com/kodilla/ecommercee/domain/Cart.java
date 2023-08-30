package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CARTS")
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "CART_ID", unique = true)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @NotNull
    private LocalDate created;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_CART_PRODUCT",
            joinColumns = @JoinColumn(name = "CART_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    @Builder.Default
    private List<Product> products = new ArrayList<>();
    
}
