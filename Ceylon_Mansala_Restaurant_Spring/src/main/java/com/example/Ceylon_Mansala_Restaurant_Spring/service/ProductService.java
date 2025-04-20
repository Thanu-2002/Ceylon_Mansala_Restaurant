package com.example.Ceylon_Mansala_Restaurant_Spring.service;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.ProductDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;

public interface ProductService {

    void saveProduct(ProductDTO productDTO, String category);
    ResponseUtil getProducts();
    void updateProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
}
