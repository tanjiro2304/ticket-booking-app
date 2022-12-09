package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.service.TicketService;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;



@UIScope
@SpringComponent
@Route(value="/history", layout = SecondaryTemplate.class)
public class HistoryView extends BaseView<HistoryPresenter> {

    @Autowired
    TicketService ticketService;
    private Grid<Tickets> ticketGrid;

    private Dialog ticketInfoDialog;

    @Override
    protected void init() {
        setGrid();
        add(ticketGrid);


    }

    public void setGrid(){
        ticketGrid = new Grid<>();
        ticketGrid.addColumn(Tickets::getPnr).setHeader("PNR");
        ticketGrid.addColumn(Tickets::getPassengerFirstName).setHeader("First Name");
        ticketGrid.addColumn(Tickets::getPassengerLastName).setHeader("Last Name");
        ticketGrid.addColumn(Tickets::getBookingId);
        ticketGrid.addColumn(Tickets::getDateOfJourney);
        ticketGrid.setItems(ticketService.getTicketOfCurrentUser());
        ticketGrid.addSelectionListener(event->{
            setTicketInfoDialog(event.getFirstSelectedItem().get());
            ticketInfoDialog.open();
        });
    }

    public void setTicketInfoDialog(Tickets ticket){
        H2 title = new H2("Ticket Details");
        ticketInfoDialog = new Dialog();

        Span pnr = new Span("PNR : " + ticket.getPnr());
        Span bookingId = new Span("Booking ID : " + ticket.getBookingId());
        Span firstName = new Span("First Name : " + ticket.getPassengerFirstName());

        Span lastName = new Span("Last Name : " + ticket.getPassengerLastName());

        Span address = new Span("Address : "+ ticket.getAddress());


        Span source = new Span("Source : " + ticket.getTransportService().getSource());

        Span destination = new Span("Destination : " + ticket.getTransportService().getDestination());

        Span trainNo = new Span("Train No : " + ticket.getTransportService().getServiceId());

        Span arrivalTime = new Span("Arrival Time : " + ticket.getTransportService().getArrival());

        Span departureTime = new Span("Departure Time : " + ticket.getTransportService().getDeparture());


        VerticalLayout verticalLayout = new VerticalLayout(pnr, bookingId, title, firstName, lastName,address,source,destination,trainNo,arrivalTime,departureTime);
        ticketInfoDialog.add(title,verticalLayout);
    }
}
