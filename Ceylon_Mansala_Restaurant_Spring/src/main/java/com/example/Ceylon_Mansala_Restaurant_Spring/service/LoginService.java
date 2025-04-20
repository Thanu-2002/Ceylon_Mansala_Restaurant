package com.example.Ceylon_Mansala_Restaurant_Spring.service;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {

    ResponseUtil login(String email, String password);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException;
    ResponseUtil getAllUsers();
}
