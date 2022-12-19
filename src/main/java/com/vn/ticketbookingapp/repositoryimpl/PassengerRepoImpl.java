package com.vn.ticketbookingapp.repositoryimpl;

import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.repository.PassengerRepo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public abstract class PassengerRepoImpl implements PassengerRepo {
//    public List<Passenger> findByTicket(Tickets ticket){
//        return findAll().stream().filter(passenger -> passenger.getTicket().getBookingId().
//                equals(ticket.getBookingId())).
//                collect(Collectors.toList());
//    }
}
