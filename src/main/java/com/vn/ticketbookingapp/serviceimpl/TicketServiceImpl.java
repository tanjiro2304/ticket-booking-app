package com.vn.ticketbookingapp.serviceimpl;

import com.vaadin.flow.server.VaadinSession;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.repository.TicketRepo;
import com.vn.ticketbookingapp.service.TicketService;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    UserService userService;

    @Autowired
    TicketRepo ticketRepoImpl;

    @Override
    public void addTicket(Tickets ticket){
        ticketRepoImpl.save(ticket);
    }



    @Override
    public List<Tickets> getTicketOfCurrentUser(String username) {
        UserEntity user = userService.getUserByUsername(username) ;
        return ticketRepoImpl.findTicketByUserEntity(user);
    }

    @Override
    public List<Passenger> getPassengerListFromTicket(Long pnr) {
        UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        for(Tickets ticket : user.getBookedTickets()){
            if(Objects.equals(ticket.getPnr(), pnr)){
                return ticket.getPassengerList();
            }
        }
        return null;
    }
}
