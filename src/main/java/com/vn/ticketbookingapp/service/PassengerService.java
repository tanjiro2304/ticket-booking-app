package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;

import java.util.List;
import java.util.Set;

public interface PassengerService {
    public Set<Passenger> getPassengerList(Tickets ticket);
}
