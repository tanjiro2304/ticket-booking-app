package com.vn.ticketbookingapp.serviceimpl;

import com.vaadin.flow.server.VaadinSession;
import com.vn.ticketbookingapp.converter.UserConverter;
import com.vn.ticketbookingapp.dto.User;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.repository.UserRepository;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepositoryImpl;

    @Override
    public List<User> findAll(){
        return userRepositoryImpl.findAll().stream().map(UserConverter::convertToDTO).collect(Collectors.toList());
    }



    @Override
    public Set<Tickets> getTicketsList(String username) {
        UserEntity user = userRepositoryImpl.findByUserName(username);
        return user.getBookedTickets();
    }

    @Override
    public TransportService getService(Tickets ticket) {
        return ticket.getTransportService();
    }


    public Set<Passenger> passengerSet(Tickets ticket){
        return ticket.getPassengerList();
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
    public UserEntity getUserByUsername(String username) {
        return userRepositoryImpl.findByUserName(username);
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
