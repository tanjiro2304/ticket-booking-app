package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.service.BookingService;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="booking-ticket", layout = SecondaryTemplate.class)
public class BookingView extends BaseView<BookingPresenter> {

    private FormLayout bookTicketLayout;

    private ComboBox<String> source;

    private ComboBox<String> destination;

    private DatePicker dateOfJourney;

    private ComboBox<String> serviceType;

    private Button searchButton;
    @Autowired
    BookingService bookingService;


    public void init(){
        bookTicketLayout = new FormLayout();
        serviceType = new ComboBox<>("Service Type");
        serviceType.setItems(bookingService.listOfServices());

        source = new ComboBox<>("Source");
        source.setItems(bookingService.stations());
        source.setClearButtonVisible(true);
        destination = new ComboBox<>("Destination");
        destination.setItems(bookingService.stations());
        destination.setClearButtonVisible(true);


        dateOfJourney = new DatePicker("Date Of Journey");

        searchButton = new Button("Search");
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ICON);
        searchButton.addClickListener(event -> {
            if(bookingService.trainFound(source.getValue(),destination.getValue())){
                Notification.show("Train Found",3000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }else{
                Notification.show("No service between the given two stations",3000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        bookTicketLayout.setColspan(searchButton,2);
        bookTicketLayout.add(source,destination,serviceType, dateOfJourney, searchButton);
        add(bookTicketLayout);
    }
}
