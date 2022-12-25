package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.service.PassengerService;
import com.vn.ticketbookingapp.service.UserService;
import com.vn.ticketbookingapp.wrapper.PassengerWrapper;
import com.vn.ticketbookingapp.wrapper.TicketWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@UIScope
@SpringComponent
public class HistoryPresenter extends BasePresenter<HistoryView> {




    @Autowired
    UserService userService;

    @Autowired
    TicketWrapper ticketWrapper;

    @Autowired
    PassengerWrapper passengerWrapper;

    @Autowired
    PassengerService passengerService;




    public Set<Tickets> getBookedTickets(String username) throws ExecutionException {
        return ticketWrapper.getTickets(username);
    }



    public Set<Passenger> getPassengers(Tickets ticket){
        return passengerWrapper.getPassengers(ticket);
    }
}
