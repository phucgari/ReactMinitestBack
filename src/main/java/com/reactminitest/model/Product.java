package com.reactminitest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "product.categories" ,attributeNodes = @NamedAttributeNode("categories"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long price;
    @ManyToMany
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;
}
