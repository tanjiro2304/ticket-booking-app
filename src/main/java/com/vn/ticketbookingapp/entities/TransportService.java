package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransportService {

    @Id
    @Column(name="service_id")
    private Long serviceId;

    @Column(name="service_type")
    private String serviceType;

    @Column(name = "time_of_departure")
    private LocalTime departure;

    @Column(name="time_of_arrival")
    private LocalTime arrival;

    @Column(name="source")
    private String source;

    @Column(name="destination")
    private String destination;


}
