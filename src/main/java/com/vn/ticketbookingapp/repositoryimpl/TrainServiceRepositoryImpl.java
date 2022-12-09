package com.vn.ticketbookingapp.repositoryimpl;

import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.repository.TrainServiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

//@UIScope

@Repository
public abstract class TrainServiceRepositoryImpl implements TrainServiceRepository {
    @Override
    public List<TransportService> findBySourceAndDestination(String source, String destination) {
        return findAll().stream().filter(transportService ->
                transportService.getSource().equals(source) &&
                        transportService.getDestination().equals(destination)).collect(Collectors.toList());
    }
}

