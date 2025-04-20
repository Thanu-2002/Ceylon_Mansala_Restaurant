package com.example.Ceylon_Mansala_Restaurant_Spring.repo;

import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
