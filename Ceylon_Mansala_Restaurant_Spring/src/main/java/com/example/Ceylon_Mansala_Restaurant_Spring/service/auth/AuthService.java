package com.example.Ceylon_Mansala_Restaurant_Spring.service.auth;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.SignupRequest;
import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);
}
