package com.example.Ceylon_Mansala_Restaurant_Spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> productList;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }
}
