package com.vn.ticketbookingapp.wrapper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.service.PassengerService;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class PassengerWrapper {

    @Autowired
    PassengerService passengerService;

    private LoadingCache<Tickets, Set<Passenger>> loadingCache = CacheBuilder.newBuilder().
            build(new CacheLoader<Tickets, Set<Passenger>>() {
                @Override
                public Set<Passenger> load(Tickets tickets) throws Exception {
                    return passengerService.getPassengerList(tickets);
                }
            });

    public Set<Passenger> getPassengers(Tickets ticket) {
        try {
            return loadingCache.get(ticket);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
