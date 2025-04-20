package com.example.Ceylon_Mansala_Restaurant_Spring.controller;

import com.example.Ceylon_Mansala_Restaurant_Spring.dto.ReservationDTO;
import com.example.Ceylon_Mansala_Restaurant_Spring.service.ReservationService;
import com.example.Ceylon_Mansala_Restaurant_Spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/saveReservation")
    @PreAuthorize("hasAuthority('USER')")
    public void saveProduct(@RequestBody ReservationDTO reservationDTO) {
        reservationService.saveReservation(reservationDTO);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getAllReservation() {
        return reservationService.getAllReservation();
    }
}
