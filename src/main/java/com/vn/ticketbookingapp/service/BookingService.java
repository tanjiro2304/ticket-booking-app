package com.vn.ticketbookingapp.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.repository.TrainServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

@SpringComponent
@UIScope
public interface BookingService {

    public HashSet<String> serviceTypes();
    public List<String> listOfServices();

    HashSet<String> stations();

    public Boolean trainFound(String source, String Destination);
}
