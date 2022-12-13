package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;

import java.util.List;

public interface PassengerService {
    public List<Passenger> getPassengerList(Tickets ticket);
}
