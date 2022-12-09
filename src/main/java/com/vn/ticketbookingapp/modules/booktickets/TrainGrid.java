package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.List;

@SpringComponent
@UIScope
public class TrainGrid extends Grid<TransportService> {

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
    }
}
