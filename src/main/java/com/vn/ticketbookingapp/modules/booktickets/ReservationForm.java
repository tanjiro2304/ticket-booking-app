package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.server.VaadinSession;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class ReservationForm extends Dialog {

    @Autowired
    TicketService ticketService;

    private FormLayout formLayout;

    private Button cancelButton;

    private TextField passengerFirstName;

    private TextField passengerLastName;

    private TextField contactNo;

    private TextArea address;

    private IntegerField age;

    private Button bookButton;

    private DatePicker datePicker;

    private Binder<Tickets> bookingBinder;

    private Tickets ticket;

    public void init(TransportService transportService) {
        ticket = new Tickets();
        formLayout = new FormLayout();
        passengerFirstName = new TextField("First Name");
        passengerLastName = new TextField("Last Name");
        address = new TextArea("Address");
        age = new IntegerField("Age");
        contactNo = new TextField("Contact No");
        bookButton = new Button("Book");
        cancelButton = new Button("Cancel");
        datePicker = new DatePicker("Date Of Journey");
        formLayout.add(passengerFirstName,passengerLastName,age,contactNo,datePicker, address, bookButton, cancelButton);
        formLayout.setColspan(address,2);

        setBinder();

        cancelButton.addClickListener(event -> close());
        bookButton.addClickListener(event -> {
            try {
                bookingBinder.writeBean(ticket);
                ticket.setPnr(generatePnrNo());
                ticket.setBookingId(generateBookingId());
                ticket.setTransportService(transportService);
                UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
                ticket.setUserEntity(user);
                Notification.show("Ticket Booked Successfully",2000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_ERROR);
                System.out.println(ticket);
                ticketService.addTicket(ticket);
                close();
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });

        add(formLayout);
    }



    private Long generatePnrNo() {
        return ThreadLocalRandom.current().nextLong(999999L,9999999L);
    }

    private Long generateBookingId(){
        return ThreadLocalRandom.current().nextLong(99999999L,999999999L);
    }

    private void setBinder(){

        bookingBinder = new Binder<>();

        bookingBinder.forField(passengerFirstName).
                withNullRepresentation("").
                withValidator(name -> name.length() >3,"Enter a Valid First Name").
                bind(Tickets::getPassengerFirstName,Tickets::setPassengerFirstName);

        bookingBinder.forField(passengerLastName).
                withNullRepresentation("").
                withValidator(name -> name.length() >3,"Enter a Valid Last Name").
                bind(Tickets::getPassengerLastName,Tickets::setPassengerLastName);

        bookingBinder.forField(address).
                withNullRepresentation("").
                withValidator(name -> name.length() >3,"Enter a Valid Address").
                bind(Tickets::getAddress,Tickets::setAddress);

        bookingBinder.forField(age).
                withValidator(age -> age > 18 && age < 99,"Enter a Valid Age").
                bind(Tickets::getAge,Tickets::setAge);

        bookingBinder.forField(contactNo).
                withNullRepresentation("").
                withValidator(name -> name.length() == 10,"Enter a Contact No").
                bind(Tickets::getContactNo,Tickets::setContactNo);

        bookingBinder.forField(datePicker).
                withValidator(name -> name.isAfter(LocalDate.now()),"Enter a Date Of Journey").
                bind(Tickets::getDateOfJourney,Tickets::setDateOfJourney);
    }

}
