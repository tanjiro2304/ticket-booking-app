package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.entities.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Tickets, Long> {

}
