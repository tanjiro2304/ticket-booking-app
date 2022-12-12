package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_passengers")
public class Passenger {

    @Id
    @Column(name="p_id")
    private String passengerId;

    @Column(name="passenger_first_name")
    private String passengerFirstName;

    @Column(name="passenger_last_name")
    private String passengerLastName;

    @Column
    private Integer age;

    @Column
    private String gender;

    @ManyToOne
    private Tickets ticket;




}
