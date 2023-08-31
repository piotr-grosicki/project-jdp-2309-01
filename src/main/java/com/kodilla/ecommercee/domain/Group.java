package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PRODUCT_GROUPS")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "GROUP_ID", unique = true)
    private Long id;
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Product> products = new ArrayList<>();
    
    @Override
    public boolean equals(Object object) {
        
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        Group group = (Group) object;
        
        if (!Objects.equals(id, group.id)) return false;
        return Objects.equals(name, group.name);
    }
    
    @Override
    public int hashCode() {
        
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
