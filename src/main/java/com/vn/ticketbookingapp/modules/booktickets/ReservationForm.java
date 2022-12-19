package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.modules.bookinghistory.TicketHistoryGrid;
import com.vn.ticketbookingapp.service.TicketService;
import com.vn.ticketbookingapp.serviceimpl.BookingServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UIScope
@SpringComponent
@Getter
public class ReservationForm extends Dialog {

    @Autowired
    BookingPresenter bookingPresenter;


    @Autowired
    TicketService ticketService;

    TransportService transportService;

    private FormLayout formLayout;

    private Button cancelButton;

    private TextField passengerFirstName;

    private TextField passengerLastName;

    private TextField contactNo;


    private IntegerField age;

    private Button bookButton;

    private BookingServiceImpl bookingService;

    private DatePicker datePicker;

    private Binder<Tickets> bookingBinder;

    private Binder<Passenger> passengerBinder;


    private Tickets ticket;

    private TextField passengerId;

    private ComboBox<String> gender;

    private Passenger passenger;

    private Set<Passenger> passengerList;

    private Button submitButton;

    private Grid<Passenger> passengerGrid;

    @Autowired
    private TicketHistoryGrid ticketHistoryGrid;

    private Button addButton;

    private Button saveButton;

    private Button removeButton;

    private Long bookingId;

    public TransportService getTransportService() {
        return transportService;
    }

    public void setTransportService(TransportService transportService) {
        this.transportService = transportService;
    }

    @PostConstruct
    public void init() {
        bookingId = getBookingPresenter().generateBookingId();
        setSizeFull();
        ticket = new Tickets();
        formLayout = new FormLayout();
        passengerId = new TextField("Passenger Id");
        passengerFirstName = new TextField("First Name");
        passengerLastName = new TextField("Last Name");

        passengerList = new HashSet<>();
        age = new IntegerField("Age");
        contactNo = new TextField("Contact No");
        bookButton = new Button("Book");
        cancelButton = new Button("Cancel");
        datePicker = new DatePicker("Date Of Journey");
        gender = new ComboBox<>("Gender");
        gender.setItems("Male","Female");
        addButton = new Button("Add");
        addButton.setIcon(VaadinIcon.PLUS.create());
        removeButton = new Button("Remove");
        removeButton.setIcon(VaadinIcon.MINUS.create());
        saveButton = new Button("Save");
        saveButton.setIcon(VaadinIcon.DISC.create());





        passengerGrid = new Grid<>();
        passengerGrid.addColumn(Passenger::getPassengerFirstName).setHeader(passengerFirstName);
        passengerGrid.addColumn(Passenger::getPassengerLastName).setHeader(passengerLastName);
        passengerGrid.addColumn(Passenger::getAge).setHeader(age);
        passengerGrid.addColumn(Passenger::getGender).setHeader(gender);
        passengerGrid.addColumn(Passenger::getPassengerId).setHeader(passengerId);


        cancelButton.addClickListener(event -> close());
        createTicket();
        bookButton.addClickListener(event -> {

            try {

                setBookingBinder();

                bookingBinder.writeBean(ticket);
                ticket.setPnr(bookingPresenter.generatePnrNo());
                ticket.setPassengerList(passengerList);
                ticket.setTransportService(transportService);
                UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
                ticket.setUserEntity(user);
                Notification.show("Ticket Booked Successfully",2000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_ERROR);
                bookingPresenter.saveTicket(ticket);
                ticketHistoryGrid.getTicketsList().add(ticket);


                close();
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });

        addButton.addClickListener(event -> {
            setPassengerBinder();
            if(passengerBinder.isValid()){
                try {
                    passenger =new Passenger();
                    passengerBinder.writeBean(passenger);
                    passenger.setTicket(ticket);
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                passengerList.add(passenger);
                passengerGrid.setItems(passengerList);


                passengerGrid.getDataProvider().refreshAll();
                Notification.show("Passenger Added Successfully",3000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
            else{
                Notification.show("Please fill the details correctly");
            }
        });

        removeButton.addClickListener(event -> {
            Passenger passenger1 = passengerGrid.getSelectedItems().stream().findFirst().orElse(null);
            if(passenger1==null){
                Notification.show("Please select a passenger to remove",3000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else{
                passengerList.remove(passenger1);
                passengerGrid.setItems(passengerList);
                passengerGrid.getDataProvider().refreshAll();
            }
        });



        HorizontalLayout horizontalLayout = new HorizontalLayout(contactNo,datePicker);
        HorizontalLayout buttons = new HorizontalLayout(addButton,removeButton);
        add(new H1("Add Passengers"), horizontalLayout,buttons,passengerGrid, bookButton,cancelButton);
    }

    public void createTicket(){
        ticket = new Tickets();
        ticket.setBookingId(bookingId);
    }





    private void setPassengerBinder(){

        passengerBinder = new Binder<>();

        passengerBinder.forField(passengerFirstName).
                withNullRepresentation("").
                withValidator(name -> name.length() >3,"Enter a Valid First Name").
                bind(Passenger::getPassengerFirstName,Passenger::setPassengerFirstName);

        passengerBinder.forField(passengerLastName).
                withNullRepresentation("").
                withValidator(name -> name.length() > 3,"Enter a Valid Last Name").
                bind(Passenger::getPassengerLastName, Passenger::setPassengerLastName);

        passengerBinder.forField(age).
                withValidator(age -> age > 18 && age < 99,"Enter a Valid Age").
                bind(Passenger::getAge,Passenger::setAge);

        passengerBinder.forField(gender).
                withValidator(s -> gender.getValue() != null,"Gender cannot be empty").
                bind(Passenger::getGender, Passenger::setGender);

        passengerBinder.forField(passengerId).withNullRepresentation("").
                withValidator(s -> passengerId.getValue() != null,"Id cannot be empty").
                bind(Passenger::getPassengerId, Passenger::setPassengerId);
    }

    public void setBookingBinder(){
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        datePicker.setMin(now);
        datePicker.setMax(now.plusDays(60));
        bookingBinder = new Binder<>();
        bookingBinder.forField(contactNo).withNullRepresentation("").withValidator(e->
            contactNo.getValue().length() == 10,"Invalid Mobile No").
                bind(Tickets::getContactNo,Tickets::setContactNo);
        bookingBinder.forField(datePicker).
                bind(Tickets::getDateOfJourney,Tickets::setDateOfJourney);
    }
}
