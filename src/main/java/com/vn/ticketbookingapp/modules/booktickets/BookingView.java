package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vn.ticketbookingapp.entities.SearchObj;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.service.BookingService;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Objects;

@Route(value = "booking-ticket", layout = SecondaryTemplate.class)
public class BookingView extends BaseView<BookingPresenter> {

    private FormLayout bookTicketLayout;

    private VerticalLayout layout;

    private ComboBox<String> source;

    private ComboBox<String> destination;

    private DatePicker dateOfJourney;

    private ComboBox<String> serviceType;

    private Button searchButton;

    @Autowired
    private TrainGrid trainGrid;
    @Autowired
    private BookingService bookingService;

    private SearchObj search;

    private Binder<SearchObj> searchBinder;

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

        setBinder();
        dateOfJourney = new DatePicker("Date Of Journey");
        dateOfJourney.setMin(LocalDate.now());
        bookTicketLayout.setColspan(searchButton, 2);
        bookTicketLayout.add(source, destination, serviceType, dateOfJourney, searchButton);
        layout.add(bookTicketLayout);

        add(layout);
        searchButton.addClickListener(event -> {
            if(searchBinder.isValid()){
                try {
                    searchBinder.writeBean(search);
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                if (bookingService.trainFound(search.getSource(), search.getDestination(), search.getServiceType())) {

                    Notification.show("Train Found", 3000, Notification.Position.TOP_END).
                            addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                     trainGrid.setItems(getPresenter().getTrains());

                    layout.add(trainGrid);

                    add(layout);

                } else {
                    Notification.show("No service between the given two stations", 3000,
                                    Notification.Position.TOP_END).
                            addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }

        });
    }

    public void setBinder(){
        searchBinder = new Binder<>();
        search = new SearchObj();

        searchBinder.forField(source).withNullRepresentation("").
                withValidator(Objects::nonNull,"Source Cannot be Empty").
                bind(SearchObj::getSource,SearchObj::setSource);

        searchBinder.forField(destination).withNullRepresentation("").
                withValidator(Objects::nonNull,"Destination Cannot be Empty").
                bind(SearchObj::getDestination,SearchObj::setDestination);

        searchBinder.forField(serviceType).withNullRepresentation("").
                withValidator(source-> !(source.isEmpty()),"Service Type Cannot be Empty").
                bind(SearchObj::getServiceType,SearchObj::setServiceType);
    }
}

