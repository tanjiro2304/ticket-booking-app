package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.dto.User;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Tickets, Long> {
    List<Tickets> findTicketByUserEntity(UserEntity user);
}
