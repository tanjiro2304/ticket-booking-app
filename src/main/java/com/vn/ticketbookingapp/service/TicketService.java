package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.entities.Tickets;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    void addTicket(Tickets tickets);
}
