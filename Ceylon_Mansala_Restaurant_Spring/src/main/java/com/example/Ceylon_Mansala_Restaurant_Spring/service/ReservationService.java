package com.example.Ceylon_Mansala_Restaurant_Spring.service;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.ReservationDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.entity.User;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;

public interface ReservationService {

    void saveReservation(ReservationDTO reservationDTO);
    User getNowUser();
    ResponseUtil getAllReservation();
}
