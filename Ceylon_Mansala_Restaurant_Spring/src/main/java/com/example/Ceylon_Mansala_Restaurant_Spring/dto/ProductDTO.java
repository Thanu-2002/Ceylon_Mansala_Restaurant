package com.example.Ceylon_Mansala_Restaurant_Spring.dto;

import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private Category category;

    public ProductDTO(String name, String description, double price, String image, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }
}
