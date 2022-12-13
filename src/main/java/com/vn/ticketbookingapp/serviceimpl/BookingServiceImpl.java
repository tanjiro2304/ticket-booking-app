package com.vn.ticketbookingapp.serviceimpl;

import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.repository.TrainServiceRepository;
import com.vn.ticketbookingapp.service.BookingService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class BookingServiceImpl implements BookingService {

    @Autowired
    TrainServiceRepository trainServiceRepository;

    private List<TransportService> transportServiceList;

    public List<TransportService> getTransportServiceList() {
        return transportServiceList;
    }

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
        transportServiceList =trainServiceRepository.findBySourceAndDestination(source, destination);
        if( transportServiceList.isEmpty()){
            return false;
        }
        return true;
    }




}
