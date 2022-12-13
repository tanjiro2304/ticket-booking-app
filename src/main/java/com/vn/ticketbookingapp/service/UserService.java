package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.dto.User;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;

import java.util.List;

public interface UserService {
    List<User> findAll();

    List<Tickets> getTicketsList(String username);

    public Boolean userExists(UserEntity userEntity);
    public void addUser(UserEntity userEntity);

    public UserEntity getUserByUsername(String username);

    Boolean ifUserExists(String username, String password);
}
