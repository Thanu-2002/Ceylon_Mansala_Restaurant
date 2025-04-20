package com.example.Ceylon_Mansala_Restaurant_Spring.service.impl;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.User;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.UserRepo;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.LoginService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoginServiceImpl implements LoginService, UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public static User user;

    @Override
    public ResponseUtil login(String email, String password) {
        if (userRepository.existsUsersByEmail(email)){
            User usersByEmail = userRepository.getUsersByEmail(email);
            if (usersByEmail.getPassword().equals(password)){
                user = usersByEmail;
                return new ResponseUtil(200, "Login successful", true);
            } else {
                return new ResponseUtil(401, "Incorrect password", false);
            }
        } else {
            return new ResponseUtil(404, "User not found", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUsersByEmail(email);

        String password;
        if (user.getPassword() == null) {
            password = "";
        } else {
            password = user.getPassword();
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), password, getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUsersByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public ResponseUtil getAllUsers() {
        List<User> all = userRepository.findAll();
        List<UserDTO> dtoList = all.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        for (UserDTO userDTO : dtoList) {
            userDTO.setReservationList(new ArrayList<>());
        }

        return new ResponseUtil(200,"get all users", dtoList);
    }
}
