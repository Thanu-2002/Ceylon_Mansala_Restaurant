package com.example.Ceylon_Mansala_Restaurant_Spring.service.impl;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.UserDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.User;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.UserRepo;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.SignUpService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsUsersByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setReservationList(new ArrayList<>());
//            userDTO.setRole("USER");
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }
}
