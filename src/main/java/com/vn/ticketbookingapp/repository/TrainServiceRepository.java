package com.vn.ticketbookingapp.repository;

import com.vn.ticketbookingapp.entities.TransportService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainServiceRepository extends JpaRepository<TransportService, String> {


    List<TransportService> findBySourceAndDestination(String source, String destination);

}
