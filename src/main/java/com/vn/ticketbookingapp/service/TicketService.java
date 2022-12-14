package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TicketService {


    public void addTicket(Tickets ticket);

    List<Tickets> getTicketOfCurrentUser(String username);

    List<Passenger> getPassengerListFromTicket(Long pnr);
}
