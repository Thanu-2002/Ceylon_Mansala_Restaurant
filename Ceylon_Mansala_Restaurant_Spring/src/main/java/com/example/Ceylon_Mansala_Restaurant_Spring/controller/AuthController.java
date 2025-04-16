package com.example.Ceylon_Mansala_Restaurant_Spring.controller;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.SignupRequest;
import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        UserDTO createdUserDTO = authService.createUser(signupRequest);

        if(createdUserDTO == null){
            return new ResponseEntity<>("User not created. Come again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }
}
