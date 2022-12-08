package com.vn.ticketbookingapp.serviceimpl;

import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.repository.TicketRepo;
import com.vn.ticketbookingapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepo ticketRepo;

    @Override
    public void addTicket(Tickets ticket){
        ticketRepo.save(ticket);
    }
}
