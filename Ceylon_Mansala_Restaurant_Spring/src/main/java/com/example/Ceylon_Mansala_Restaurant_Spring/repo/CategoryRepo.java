package com.example.Ceylon_Mansala_Restaurant_Spring.repo;

import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> getCategoriesByName(String name);
}
