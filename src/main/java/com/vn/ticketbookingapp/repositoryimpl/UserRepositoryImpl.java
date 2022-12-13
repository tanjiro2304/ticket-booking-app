package com.vn.ticketbookingapp.repositoryimpl;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class UserRepositoryImpl implements UserRepository {
    List<Tickets> currentUserTickets;

}
