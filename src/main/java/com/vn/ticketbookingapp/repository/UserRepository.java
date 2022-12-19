package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(value = "UserEntity.bookedTickets")
    UserEntity findByUserName(String username);




}

