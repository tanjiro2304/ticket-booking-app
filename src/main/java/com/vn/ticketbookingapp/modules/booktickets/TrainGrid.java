package com.vn.ticketbookingapp.modules.booktickets;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TrainGrid extends VerticalLayout {

    @Autowired
    BookingService bookingService;

    public TrainGrid(List<TransportService> transportServiceList){
        Grid<TransportService> transportServiceGrid = new Grid<>();
        transportServiceGrid.addColumn(TransportService::getServiceId).setHeader("Service Id");
        transportServiceGrid.addColumn(TransportService::getSource).setHeader("Source");
        transportServiceGrid.addColumn(TransportService::getDestination).setHeader("Destination");
        transportServiceGrid.addColumn(TransportService::getServiceType).setHeader("Service Type");
        transportServiceGrid.addColumn(TransportService::getArrival).setHeader("Arrival");
        transportServiceGrid.addColumn(TransportService::getDeparture).setHeader("Departure");
        if(!(transportServiceList.isEmpty())){
            transportServiceGrid.setItems(transportServiceList);
            add(transportServiceGrid);
        }


    }
}
