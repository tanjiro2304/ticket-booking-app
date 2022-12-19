package com.vn.ticketbookingapp.serviceimpl;

import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.repository.PassengerRepo;
import com.vn.ticketbookingapp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepo passengerRepo;




    @Override
    public List<Passenger> getPassengerList(Tickets ticket) {
        List<Passenger> passengerList = passengerRepo.findByTicket(ticket);
        return passengerRepo.findByTicket(ticket);
    }
}
