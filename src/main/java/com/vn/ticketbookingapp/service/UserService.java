package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.entities.User;

public interface UserService {
    public Boolean userExists(User user);
    public void addUser(User user);

    Boolean ifUserExists(String username, String password);
}
