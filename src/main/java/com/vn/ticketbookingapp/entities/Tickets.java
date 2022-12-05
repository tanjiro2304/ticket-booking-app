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
public class Tickets {

    @Id
    private Long bookingId;

    @Column(name = "pnr")
    private Long pnr;

    @Column(name = "date_of_journey")
    private LocalDate dateOfJourney;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    //Column will be service_id

    private TransportService transportService;


}
