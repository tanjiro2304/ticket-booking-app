package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_transport_service")
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

    @Column(name="travel_time")
    private LocalTime travelTime;
}
