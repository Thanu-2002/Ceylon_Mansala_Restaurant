package com.example.Ceylon_Mansala_Restaurant_Spring.repo;

import com.example.Ceylon_Mansala_Restaurant_Spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsUsersByEmail(String email);

    User getUsersByEmail(String email);

}
