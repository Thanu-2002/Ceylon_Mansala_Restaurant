package com.example.Ceylon_Mansala_Restaurant_Spring.dto;

import com.example.Ceylon_Mansala_Restaurant_Spring.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;
    private String name;
    private int numberOfPerson;
    private Date date;
    private Time time;
    private String tableType;
    private User user;

    public ReservationDTO(String name, int numberOfPerson, Date date, Time time, String tableType, User user) {
        this.name = name;
        this.numberOfPerson = numberOfPerson;
        this.date = date;
        this.time = time;
        this.tableType = tableType;
        this.user = user;
    }
}
