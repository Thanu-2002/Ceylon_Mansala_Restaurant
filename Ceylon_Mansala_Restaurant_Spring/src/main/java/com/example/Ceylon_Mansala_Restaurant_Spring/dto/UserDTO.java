package com.example.Ceylon_Mansala_Restaurant_Spring.dto;


import com.example.Ceylon_Mansala_Restaurant_Spring.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Long tel;
    private String role;
    private List<Reservation> reservationList;

    public UserDTO(String name, String email, String password, Long tel, String role,  List<Reservation> reservationList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.role = role;
        this.reservationList = reservationList;
    }

    public UserDTO(Long id, String name, String email, String password, Long tel, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.role = role;
    }
}
