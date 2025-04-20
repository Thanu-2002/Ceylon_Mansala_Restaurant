package com.example.Ceylon_Mansala_Restaurant_Spring.dto;

import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private List<Product> productList;

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }
}
