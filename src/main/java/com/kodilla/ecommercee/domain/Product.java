package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;
    
    @NotNull
    private String name;
    
    private String description;
    
    @NotNull
    private double price;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Cart> carts;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCT_ORDER",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORDER_ID")
    )
    private List<Order> orders;
    
    @Override
    public boolean equals(Object object) {
        
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        Product product = (Product) object;
        
        if (Double.compare(price, product.price) != 0) return false;
        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(description, product.description)) return false;
        return Objects.equals(group, product.group);
    }
    
    @Override
    public int hashCode() {
        
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
