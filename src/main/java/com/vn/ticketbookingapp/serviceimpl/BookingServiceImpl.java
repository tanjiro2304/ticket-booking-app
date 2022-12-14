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
        return trainServiceRepository.findAll().
                stream().
                map(TransportService::getServiceType).collect(Collectors.toCollection(HashSet::new));
    }

    public HashSet<String> stations() {



        return trainServiceRepository.findAll().
                stream().
                map(TransportService::getSource).collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public List<String> listOfServices() {
        return new ArrayList<>(serviceTypes());
    }

    public List<TransportService> filterByServiceType(String serviceType, List<TransportService> transportServiceList){
        return transportServiceList.stream().
                filter(transportService -> transportService.getServiceType().equals(serviceType)).
                collect(Collectors.toList());
    }

    @Override
    public Boolean trainFound(String source, String destination, String serviceType) {
        transportServiceList =trainServiceRepository.findBySourceAndDestination(source, destination);

        transportServiceList = filterByServiceType(serviceType,getTransportServiceList());
        if( transportServiceList.isEmpty()){
            return false;
        }
        return true;
    }




}
