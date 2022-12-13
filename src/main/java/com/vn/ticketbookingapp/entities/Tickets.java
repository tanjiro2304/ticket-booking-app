package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_tickets")

public class Tickets {

    @Id
    private Long bookingId;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "pnr")
    private Long pnr;

    @Column(name = "date_of_journey")
    private LocalDate dateOfJourney;

    @Column
    @OneToMany(mappedBy = "ticket")
    List<Passenger> passengerList;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private TransportService transportService;


}
