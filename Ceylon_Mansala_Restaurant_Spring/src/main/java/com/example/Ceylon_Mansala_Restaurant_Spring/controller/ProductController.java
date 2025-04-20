package com.example.Ceylon_Mansala_Restaurant_Spring.controller;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.ProductDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.ProductService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/saveProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void saveProduct(@RequestBody ProductDTO productDTO, @Param("category") String category) {
        productService.saveProduct(productDTO,category);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseUtil getAllProduct() {
        return productService.getProducts();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateProduct(@PathVariable("productId") String productId) {
        System.out.println(productId);
        productService.deleteProduct(Long.valueOf(productId.trim()));
    }
}
