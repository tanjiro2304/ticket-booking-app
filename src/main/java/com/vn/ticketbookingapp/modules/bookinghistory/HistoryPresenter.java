package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.service.PassengerService;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.krb5.internal.Ticket;

import java.util.List;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class HistoryPresenter extends BasePresenter<HistoryView> {



    @Autowired
    UserService userService;

    @Autowired
    PassengerService passengerService;

    List<Tickets> ticketsList;


    public List<Tickets> getBookedTickets(String username){
        ticketsList = userService.getTicketsList(username);
        for (Tickets ticket:ticketsList) {
            ticket.getPassengerList();
        }
        return ticketsList;
    }



    public List<Passenger> getPassengers(Tickets ticket){


        return passengerService.getPassengerList(ticket);
    }
}
