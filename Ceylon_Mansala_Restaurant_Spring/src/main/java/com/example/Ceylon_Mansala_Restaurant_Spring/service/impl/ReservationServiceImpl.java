package com.example.Ceylon_Mansala_Restaurant_Spring.service.impl;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.ReservationDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.LoginData;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Reservation;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.User;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.LoginDataRepository;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.ReservationRepo;
import com.example.Ceylon_Mansala_Restaurant_Spring.repo.UserRepo;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.ReservationService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.JwtUtil;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private LoginDataRepository loginDataRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void saveReservation(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservation.setUser(getNowUser());
        reservationRepo.save(reservation);
    }

    @Override
    public User getNowUser(){
        LoginData byId = loginDataRepository.findById(1);
        String username = jwtUtil.getUsernameFromToken(byId.getToken());
        return userRepo.getUsersByEmail(username);
    }

    @Override
    public ResponseUtil getAllReservation() {
        List<Reservation> all = reservationRepo.findAll();
        List<ReservationDTO> dtoList = all.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());

        for (ReservationDTO reservationDTO : dtoList) {
            reservationDTO.getUser().setReservationList(new ArrayList<>());
        }

        return new ResponseUtil(200,"All Reservation",dtoList);
    }
}
