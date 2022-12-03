package com.vn.ticketbookingapp.service;

import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.repository.UserRepository;
import com.vn.ticketbookingapp.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService{

    @Autowired
    UserRepository userRepositoryImpl;

    public Boolean userExists(User user){
        List<User> userList = userRepositoryImpl.findAll();

        return true;
    }

    public void addUser(User user){
        userRepositoryImpl.save(user);
    }
}
