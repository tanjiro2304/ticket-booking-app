package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.entities.TransportService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainServiceRepository extends JpaRepository<TransportService, String> {


}
