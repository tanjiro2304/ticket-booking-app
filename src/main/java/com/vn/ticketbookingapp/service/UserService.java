package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.dto.User;
import com.vn.ticketbookingapp.entities.UserEntity;

import java.util.List;

public interface UserService {
    List<User> findAll();

    public Boolean userExists(UserEntity userEntity);
    public void addUser(UserEntity userEntity);

    Boolean ifUserExists(String username, String password);
}
