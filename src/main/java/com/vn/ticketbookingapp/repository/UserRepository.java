package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


}
