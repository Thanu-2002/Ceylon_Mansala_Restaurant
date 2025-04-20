package com.example.Ceylon_Mansala_Restaurant_Spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int numberOfPerson;
    private Date date;
    private Time time;
    private String tableType;

    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    public Reservation(String name, int numberOfPerson, Date date, Time time, String tableType, User user) {
        this.name = name;
        this.numberOfPerson = numberOfPerson;
        this.date = date;
        this.time = time;
        this.tableType = tableType;
        this.user = user;
    }
}
