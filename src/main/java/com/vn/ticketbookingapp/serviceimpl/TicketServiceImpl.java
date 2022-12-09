package com.vn.ticketbookingapp.serviceimpl;

import com.vaadin.flow.server.VaadinSession;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.repository.TicketRepo;
import com.vn.ticketbookingapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepo ticketRepoImpl;

    @Override
    public void addTicket(Tickets ticket){
        ticketRepoImpl.save(ticket);
    }

    @Override
    public List<Tickets> getTicketOfCurrentUser() {
        UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        return ticketRepoImpl.findTicketByUserEntity(user);
    }
}
