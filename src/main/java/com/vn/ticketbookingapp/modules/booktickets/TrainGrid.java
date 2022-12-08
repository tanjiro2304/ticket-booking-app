package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TrainGrid extends VerticalLayout {

    ReservationForm reservationForm;
    @Autowired
    BookingService bookingService;

    public TrainGrid(List<TransportService> transportServiceList){
        Grid<TransportService> transportServiceGrid = new Grid<>();
        Label instruction = new Label("*Select Train and Click on Book to Book Ticket");
        instruction.getStyle().set("text-color","red");
        transportServiceGrid.addColumn(TransportService::getServiceId).setHeader("Service Id");
        transportServiceGrid.addColumn(TransportService::getSource).setHeader("Source");
        transportServiceGrid.addColumn(TransportService::getDestination).setHeader("Destination");
        transportServiceGrid.addColumn(TransportService::getServiceType).setHeader("Service Type");
        transportServiceGrid.addColumn(TransportService::getArrival).setHeader("Arrival");
        transportServiceGrid.addColumn(TransportService::getDeparture).setHeader("Departure");
        transportServiceGrid.addColumn(TransportService::getTravelTime).setHeader("Travel Time");
        transportServiceGrid.addComponentColumn(book->{
            Button bookButton = new Button("Book Tickets");
            bookButton.addClickListener(event -> {
                if(transportServiceGrid.getSelectedItems().isEmpty()){
                    Notification.show("Please select a train to book ticket.",
                            2000,
                            Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                else{
                    reservationForm = new ReservationForm(transportServiceGrid.getSelectedItems().
                            stream().
                            findFirst().
                            get());
                    reservationForm.open();
                }

            });
            return bookButton;
        });

        if(!(transportServiceList.isEmpty())){
            transportServiceGrid.setItems(transportServiceList);
            add(instruction,transportServiceGrid);
        }


    }
}
