package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    Set<Passenger> passengerList;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private TransportService transportService;


}
