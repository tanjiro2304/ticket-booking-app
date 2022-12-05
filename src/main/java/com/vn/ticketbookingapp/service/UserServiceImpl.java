package com.vn.ticketbookingapp.service;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UIScope
@SpringComponent
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepositoryImpl;



    @Override
    public Boolean userExists(User user){
        List<User> userList = userRepositoryImpl.findAll();

        return true;
    }

    @Override
    public void addUser(User user){
        userRepositoryImpl.save(user);
    }

    @Override
    public Boolean ifUserExists(String username, String password){
        List<User> userList = userRepositoryImpl.findAll();
        for(User user: userList){
            if(user.getUserName().equals(username)){
                if(user.getPassword().equals(password)){
                    VaadinSession.getCurrent().setAttribute("user",user);
                    return true;
                }
            }
        }
        return false;
    }
}
