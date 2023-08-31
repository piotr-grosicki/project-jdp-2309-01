package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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
    
    @ManyToOne(fetch = FetchType.LAZY)
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
    
    @Override
    public boolean equals(Object object) {
        
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        Cart cart = (Cart) object;
        
        if (!Objects.equals(id, cart.id)) return false;
        if (!Objects.equals(user, cart.user)) return false;
        return Objects.equals(created, cart.created);
    }
    
    @Override
    public int hashCode() {
        
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
