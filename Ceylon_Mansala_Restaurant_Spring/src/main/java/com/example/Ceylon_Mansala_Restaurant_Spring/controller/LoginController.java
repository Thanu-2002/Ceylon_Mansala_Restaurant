package com.example.Ceylon_Mansala_Restaurant_Spring.controller;

import com.example.Ceylon_Mansala_Restaurant_Spring.config.JwtFilter;
import com.example.Ceylon_Mansala_Restaurant_Spring.dto.AuthDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.LoginService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.JwtUtil;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    @Autowired
    private JwtFilter jwtFilter;

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;
    private final ResponseUtil responseUtil;

    //constructor injection
    public LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, LoginService loginService, ResponseUtil responseUtil) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.responseUtil = responseUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseUtil> authenticate(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseUtil(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
        }

        UserDTO loadedUser = loginService.loadUserDetailsByUsername(userDTO.getEmail());
        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseUtil(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseUtil(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail(loadedUser.getEmail());
        authDTO.setToken(token);

        jwtFilter.saveOrUpdateLoginToken(token);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseUtil(VarList.Created, loadedUser.getRole(), authDTO));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getAllUsers() {
        return loginService.getAllUsers();
    }

}
