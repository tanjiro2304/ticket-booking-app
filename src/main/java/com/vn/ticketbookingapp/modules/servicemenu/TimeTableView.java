package com.vn.ticketbookingapp.modules.servicemenu;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.repository.TrainServiceRepository;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="/time-table", layout= SecondaryTemplate.class)
public class TimeTableView extends VerticalLayout {

    @Autowired
    TrainServiceRepository trainServiceRepository;

    Grid<TransportService> transportServiceGrid;

    @PostConstruct
    public void init(){

        transportServiceGrid = new Grid<>();
        transportServiceGrid.addColumn(TransportService::getServiceId).setHeader("Service Id");
        transportServiceGrid.addColumn(TransportService::getSource).setHeader("Source");
        transportServiceGrid.addColumn(TransportService::getDestination).setHeader("Destination");
        transportServiceGrid.addColumn(TransportService::getServiceType).setHeader("Service Type");
        transportServiceGrid.addColumn(TransportService::getArrival).setHeader("Arrival");
        transportServiceGrid.addColumn(TransportService::getDeparture).setHeader("Departure");
        transportServiceGrid.setItems(trainServiceRepository.findAll());
        add(transportServiceGrid);
    }
}
