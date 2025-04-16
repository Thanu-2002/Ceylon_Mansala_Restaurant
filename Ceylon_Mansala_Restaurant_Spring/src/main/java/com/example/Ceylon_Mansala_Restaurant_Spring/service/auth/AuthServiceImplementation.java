package com.example.Ceylon_Mansala_Restaurant_Spring.service.auth;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.SignupRequest;
import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.User;
import com.example.Ceylon_Mansala_Restaurant_Spring.enums.UserRole;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepo userRepo;


    public AuthServiceImplementation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDTO createUser(SignupRequest signupRequest){
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepo.save(user);
        UserDTO createdUserDTO = new UserDTO();
        createdUserDTO.setId(createdUser.getId());
        createdUserDTO.setName(signupRequest.getName());
        createdUserDTO.setEmail(createdUser.getEmail());
        createdUserDTO.setUserRole(createdUser.getUserRole());
        return createdUserDTO;
    }
}
