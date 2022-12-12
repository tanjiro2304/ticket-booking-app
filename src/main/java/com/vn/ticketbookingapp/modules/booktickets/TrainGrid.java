package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.TransportService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class TrainGrid extends Grid<TransportService> {

    @Autowired
    ReservationForm reservationForm;

    @PostConstruct
    public void init(){
        Label instruction = new Label("*Select Train and Click on Book to Book Ticket");
        instruction.getStyle().set("color","red");
        addColumn(TransportService::getServiceId).setHeader("Service Id");
        addColumn(TransportService::getSource).setHeader("Source");
        addColumn(TransportService::getDestination).setHeader("Destination");
        addColumn(TransportService::getServiceType).setHeader("Service Type");
        addColumn(TransportService::getArrival).setHeader("Arrival");
        addColumn(TransportService::getDeparture).setHeader("Departure");
        addColumn(TransportService::getTravelTime).setHeader("Travel Time");
        addComponentColumn(book -> {
            Button bookButton = new Button("Book Tickets");
            bookButton.addClickListener(event -> {
                if (getSelectedItems().isEmpty()) {
                    Notification.show("Please select a train to book ticket.",
                            2000,
                            Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {
                    VaadinSession.getCurrent().setAttribute("selectedTrain",getSelectedItems().
                            stream().
                            findFirst().
                            get());
                    reservationForm.open();
                }
            });
            return bookButton;
        });

    }
}
