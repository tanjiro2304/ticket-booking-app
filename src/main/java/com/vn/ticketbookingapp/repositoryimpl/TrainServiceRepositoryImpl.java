package com.vn.ticketbookingapp.repositoryimpl;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.repository.TrainServiceRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

//@UIScope
//@SpringComponent
@Repository
public abstract class TrainServiceRepositoryImpl implements TrainServiceRepository {
    @Override
    public TransportService findBySourceAndDestination(String source, String destination) {
        return findAll().stream().filter(transportService ->
                transportService.getSource().equals(source) && transportService.getDestination().equals(destination)).
                findFirst().get();
    }
}

