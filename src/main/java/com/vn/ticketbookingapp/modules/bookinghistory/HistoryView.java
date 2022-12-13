package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.service.TicketService;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@UIScope
@SpringComponent
@Route(value="/history", layout = SecondaryTemplate.class)
public class HistoryView extends BaseView<HistoryPresenter> {


    private Grid<Tickets> ticketGrid;

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
    private Tickets ticket;

    @Override
    protected void init() {
        setGrid();
        add(ticketGrid);
    }

    public void setGrid(){
        ticket = new Tickets();
        ticketGrid = new Grid<>();
        ticketGrid.addColumn(Tickets::getBookingId).setHeader("Booking Id");
        ticketGrid.addColumn(Tickets::getDateOfJourney).setHeader("Date Of Journey");
        UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        ticketGrid.setItems(getPresenter().getBookedTickets(user.getUserName()));
        ticketGrid.addSelectionListener(event->{
            ticket = event.getFirstSelectedItem().get();

            setTicketInfoDialog(ticket);
            ticketInfoDialog.open();
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
        List<Passenger> passengerList = getPresenter().getPassengers(ticket);
        passengerGrid.setItems(passengerList);

        VerticalLayout verticalLayout = new VerticalLayout(pnr, bookingId, title,source,destination,trainNo,arrivalTime,departureTime);
        ticketInfoDialog.add(title,verticalLayout,passengerGrid);
    }
}
