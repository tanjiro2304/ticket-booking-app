package com.vn.ticketbookingapp.repositoryimpl;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public abstract class UserRepositoryImpl implements UserRepository {
//    @Override
//    public Set<Tickets> bookedTickets(String username) {
//        UserEntity user = findByUserName(username);
//        return new HashSet<>(user.getBookedTickets());
//    }
}
