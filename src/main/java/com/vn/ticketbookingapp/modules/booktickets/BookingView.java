package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.service.BookingService;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "booking-ticket", layout = SecondaryTemplate.class)
public class BookingView extends BaseView<BookingPresenter> {

    private FormLayout bookTicketLayout;

    private VerticalLayout layout;

    private ComboBox<String> source;

    private ComboBox<String> destination;

    private DatePicker dateOfJourney;

    private ComboBox<String> serviceType;

    private Button searchButton;

//    @Autowired
//    private ReservationForm reservationForm;

    @Autowired
    private TrainGrid trainGrid;
    @Autowired
    BookingService bookingService;


    public void init() {
        layout = new VerticalLayout();
        bookTicketLayout = new FormLayout();
        bookTicketLayout.setHeight("45%");
        serviceType = new ComboBox<>("Service Type");
        serviceType.setItems(bookingService.listOfServices());


        source = new ComboBox<>("Source");
        source.setItems(bookingService.stations());
        source.setClearButtonVisible(true);
        destination = new ComboBox<>("Destination");
        destination.setItems(bookingService.stations());
        destination.setClearButtonVisible(true);
        searchButton = new Button("Search");
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ICON);

        dateOfJourney = new DatePicker("Date Of Journey");
        bookTicketLayout.setColspan(searchButton, 2);
        bookTicketLayout.add(source, destination, serviceType, dateOfJourney, searchButton);
        layout.add(bookTicketLayout);

        add(layout);
        searchButton.addClickListener(event -> {
            if (bookingService.trainFound(source.getValue(), destination.getValue())) {
                Notification.show("Train Found", 3000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                trainGrid.setItems(getPresenter().getTrains());

                layout.add(trainGrid);

                add(layout);

            } else {
                Notification.show("No service between the given two stations", 3000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_ERROR);

                layout.add(bookTicketLayout);
                add(layout);
            }
        });
    }

//    public void setTrainGrid() {
//        trainGrid.addComponentColumn(book -> {
//            Button bookButton = new Button("Book Tickets");
//            bookButton.addClickListener(event -> {
//                if (trainGrid.getSelectedItems().isEmpty()) {
//                    Notification.show("Please select a train to book ticket.",
//                            2000,
//                            Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_ERROR);
//                } else {
//                        VaadinSession.getCurrent().setAttribute("selectedTrain",trainGrid.getSelectedItems().
//                                stream().
//                                findFirst().
//                                get());
//                        reservationForm.open();
//                }
//
//            });
//            return bookButton;
//        });

//            if(!(bookingPresenter.getTrains().isEmpty())){
//                transportServiceGrid.setItems(bookingPresenter.getTrains());
//                add(instruction,transportServiceGrid);
//            }

//    }


}

