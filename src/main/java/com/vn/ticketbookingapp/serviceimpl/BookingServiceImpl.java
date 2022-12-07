package com.vn.ticketbookingapp.serviceimpl;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.repository.TrainServiceRepository;
import com.vn.ticketbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    TrainServiceRepository trainServiceRepository;

    @Override
    public HashSet<String> serviceTypes() {
        return new HashSet<>(trainServiceRepository.findAll().
                stream().
                map(TransportService::getServiceType).
                collect(Collectors.toSet())) ;
    }

    public HashSet<String> stations() {
        return new HashSet<>(trainServiceRepository.findAll().
                stream().
                map(TransportService::getSource).
                collect(Collectors.toSet())) ;
    }

    @Override
    public List<String> listOfServices() {
        List<String> allStations = new ArrayList<>(serviceTypes());
        return allStations;
    }

    @Override
    public Boolean trainFound(String source, String destination) {
        if( trainServiceRepository.findBySourceAndDestination(source, destination) != null){
            return true;
        }
        return false;
    }


}
