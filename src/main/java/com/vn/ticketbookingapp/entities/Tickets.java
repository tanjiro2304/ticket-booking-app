package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_tickets")

public class Tickets {

    @Id
    private Long bookingId;

    @Column(name="passenger_first_name")
    private String passengerFirstName;

    @Column(name="passenger_last_name")
    private String passengerLastName;

    @Column(name="address")
    private String address;

    @Column
    private Integer age;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "pnr")
    private Long pnr;

    @Column(name = "date_of_journey")
    private LocalDate dateOfJourney;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private TransportService transportService;


}
