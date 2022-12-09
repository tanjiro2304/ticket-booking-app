package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.service.BookingService;
import com.vn.ticketbookingapp.service.TicketService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@UIScope
@Getter
public class BookingPresenter extends BasePresenter<BookingView> {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TicketService ticketServiceImpl;

    public void saveTicket(Tickets ticket){
        ticketServiceImpl.addTicket(ticket);
    }

    public List<TransportService> getTrains(){
        return bookingService.getTransportServiceList();
    }
}
