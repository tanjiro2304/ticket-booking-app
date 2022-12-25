package com.vn.ticketbookingapp.serviceimpl;

import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.repository.PassengerRepo;
import com.vn.ticketbookingapp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepo passengerRepo;




    @Override
    public Set<Passenger> getPassengerList(Tickets ticket) {
        Set<Passenger> passengerList = new HashSet<>(passengerRepo.findByTicket(ticket));
        return passengerList;
    }
}
