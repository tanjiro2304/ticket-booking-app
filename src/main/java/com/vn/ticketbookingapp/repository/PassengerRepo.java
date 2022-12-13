package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PassengerRepo extends JpaRepository<Passenger, String> {

    List<Passenger> findByTicket(Tickets ticket);
}
