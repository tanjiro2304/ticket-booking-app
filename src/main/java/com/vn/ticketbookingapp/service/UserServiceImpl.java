package com.vn.ticketbookingapp.service;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.converter.UserConverter;
import com.vn.ticketbookingapp.dto.User;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepositoryImpl;

    @Override
    public List<User> findAll(){
        return userRepositoryImpl.findAll().stream().map(UserConverter::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public Boolean userExists(UserEntity userEntity){
        List<UserEntity> userEntityList = userRepositoryImpl.findAll();
        if(userEntityList.contains(userEntity)){
            return true;
        }
        return false;
    }

    @Override
    public void addUser(UserEntity userEntity){
        userRepositoryImpl.save(userEntity);
    }

    @Override
    public Boolean ifUserExists(String username, String password){

        for(UserEntity userEntity : userRepositoryImpl.findAll()){
            if(userEntity.getUserName().equals(username)){
                if(userEntity.getPassword().equals(password)){
                    VaadinSession.getCurrent().setAttribute("user", userEntity);
                    return true;
                }
            }
        }
        return false;
    }
}
