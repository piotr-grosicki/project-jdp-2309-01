package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private double price;

   @ManyToOne
   @JoinColumn(name="group_id")
    private Group group;

}
