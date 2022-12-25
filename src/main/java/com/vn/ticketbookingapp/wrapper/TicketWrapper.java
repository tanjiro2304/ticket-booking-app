package com.vn.ticketbookingapp.wrapper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class TicketWrapper {
    @Autowired
    UserService userService;

    private LoadingCache<String, Set<Tickets>> loadingCache = CacheBuilder.newBuilder().
            build(new CacheLoader<String, Set<Tickets>>() {
        @Override
        public Set<Tickets> load(String userName) throws Exception {
            return userService.getTicketsList(userName);
        }
    });

    public Set<Tickets> getTickets(String username) {
        try {
            return loadingCache.get(username);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }



}
