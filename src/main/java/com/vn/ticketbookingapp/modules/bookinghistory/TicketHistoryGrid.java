package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@UIScope
@SpringComponent
@Getter
public class TicketHistoryGrid extends Grid<Tickets> {
    private Tickets ticket;
    private Span pnr;
    private Span departureTime;
    private Span arrivalTime ;
    private Span destination;
    private Span trainNo;
    private Span bookingId;
    private H2 title;
    private Span source;

    private Grid<Passenger> passengerGrid;

    private Dialog ticketInfoDialog;

    private Set<Tickets> ticketsList;

    @Autowired
    private HistoryPresenter historyPresenter;

    @PostConstruct
    public void init(){
        ticket = new Tickets();
        UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        ticketsList = historyPresenter.getBookedTickets(user.getUserName());
        addColumn(Tickets::getBookingId).setHeader("Booking Id");
        addColumn(Tickets::getDateOfJourney).setHeader("Date Of Journey");

        setItems(ticketsList);
        addSelectionListener(event->{
            ticket = event.getFirstSelectedItem().orElse(null);
            if(ticket==null){
                Notification.show("Please Select A Ticket",3000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            else{
                setTicketInfoDialog(ticket);
                ticketInfoDialog.open();
            }


        });
    }

    public void setTicketInfoDialog(Tickets ticket){
        title = new H2("Ticket Details");
        ticketInfoDialog = new Dialog();
        ticketInfoDialog.setSizeFull();


        pnr = new Span("PNR : " + ticket.getPnr());
        bookingId = new Span("Booking ID : " + ticket.getBookingId());


        source = new Span("Source : " + ticket.getTransportService().getSource());

        destination = new Span("Destination : " + ticket.getTransportService().getDestination());

        trainNo = new Span("Train No : " + ticket.getTransportService().getServiceId());

        arrivalTime = new Span("Arrival Time : " + ticket.getTransportService().getArrival());

        departureTime = new Span("Departure Time : " + ticket.getTransportService().getDeparture());

        passengerGrid = new Grid<>();
        passengerGrid.addColumn(Passenger::getPassengerFirstName).setHeader("First Name");
        passengerGrid.addColumn(Passenger::getPassengerLastName).setHeader("Last Name");
        passengerGrid.addColumn(Passenger::getAge).setHeader("Age");
        passengerGrid.addColumn(Passenger::getGender).setHeader("Gender");
        passengerGrid.addColumn(Passenger::getPassengerId).setHeader("Passenger Id");
        Set<Passenger> passengerList = historyPresenter.getPassengers(ticket);
        passengerGrid.setItems(passengerList);

        VerticalLayout verticalLayout = new VerticalLayout(pnr,
                bookingId,
                title,source,
                destination,
                trainNo,
                arrivalTime,
                departureTime);
        ticketInfoDialog.add(title,verticalLayout,new H4("Passenger's List"),passengerGrid);
    }
}
