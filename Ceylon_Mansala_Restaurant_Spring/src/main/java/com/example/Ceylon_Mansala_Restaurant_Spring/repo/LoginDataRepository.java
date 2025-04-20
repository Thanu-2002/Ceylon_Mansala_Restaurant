package com.example.Ceylon_Mansala_Restaurant_Spring.repo;

import com.example.Ceylon_Mansala_Restaurant_Spring.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDataRepository extends JpaRepository<LoginData,Integer> {
    LoginData findById(int id);;
}
