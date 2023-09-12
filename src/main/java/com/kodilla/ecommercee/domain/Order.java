package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
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
    
    private LocalDate orderDate;
    
    private String status;
    
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "JOIN_PRODUCT_ORDER",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    @Builder.Default
    private List<Product> products = new ArrayList<>();
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return Objects.equals(id, order.id) &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, orderDate, status);
    }
}
    


