package com.example.Ceylon_Mansala_Restaurant_Spring.service.impl;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.ProductDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Category;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Product;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.CategoryRepo;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.ProductRepo;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.ProductService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveProduct(ProductDTO productDTO, String category) {
        List<Category> categoriesByName = categoryRepo.getCategoriesByName(category);
        productDTO.setCategory(categoriesByName.get(0));
        Product product = modelMapper.map(productDTO, Product.class);
        productRepo.save(product);
    }

    @Override
    public ResponseUtil getProducts() {
        List<Product> all = productRepo.findAll();
        List<ProductDTO> dtoList = all.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        for (ProductDTO productDTO : dtoList) {
            productDTO.getCategory().setProductList(new ArrayList<>());
        }

        return new ResponseUtil(200,"All Product", dtoList);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Category referenceById = categoryRepo.getReferenceById(product.getCategory().getId());
        product.setCategory(referenceById);
        productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
