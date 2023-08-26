package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CARTS")
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    Long id;
    
    @ManyToOne(targetEntity = User.class,
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User user;
    
    @NotNull
    LocalDate created;
    
    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_CART_PRODUCT",
            joinColumns = @JoinColumn(name = "CART_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    List<Product> products = new ArrayList<>();*/
    
}
