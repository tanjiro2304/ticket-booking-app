package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long aLong);
}
