package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.modules.booktickets.ReservationForm;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.service.PassengerService;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.krb5.internal.Ticket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class HistoryPresenter extends BasePresenter<HistoryView> {




    @Autowired
    UserService userService;

    @Autowired
    PassengerService passengerService;




    public Set<Tickets> getBookedTickets(String username){
        return userService.getTicketsList(username);
    }



    public Set<Passenger> getPassengers(Tickets ticket){
        return userService.passengerSet(ticket);
    }
}
