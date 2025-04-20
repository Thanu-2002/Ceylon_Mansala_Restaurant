package com.example.Ceylon_Mansala_Restaurant_Spring.controller;

import com.example.Ceylon_Mansala_Restaurant_Spring.config.JwtFilter;
import com.example.Ceylon_Mansala_Restaurant_Spring.dto.AuthDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.SignUpService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.JwtUtil;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class SignUpController {

    @Autowired
    private JwtFilter jwtFilter;

    private final SignUpService signUpService;
    private final JwtUtil jwtUtil;

    //constructor injection
    public SignUpController(SignUpService signUpService, JwtUtil jwtUtil) {
        this.signUpService = signUpService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping(value = "/register")
    public ResponseEntity<ResponseUtil> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            int res = signUpService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    jwtFilter.saveOrUpdateLoginToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseUtil(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseUtil(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseUtil(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
