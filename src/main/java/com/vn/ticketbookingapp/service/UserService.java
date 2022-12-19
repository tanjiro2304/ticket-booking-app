package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.dto.User;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.entities.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> findAll();

    Set<Tickets> getTicketsList(String username);

    TransportService getService(Tickets ticket);

    public Boolean userExists(UserEntity userEntity);
    public void addUser(UserEntity userEntity);

    Set<Passenger> passengerSet(Tickets ticket);

    public UserEntity getUserByUsername(String username);

    Boolean ifUserExists(String username, String password);
}
