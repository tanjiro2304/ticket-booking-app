package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UIScope
@SpringComponent
public class HistoryPresenter extends BasePresenter<HistoryView> {

    @Autowired
    TicketRepo ticketRepo;


    public List<Tickets> getBookedTickets(){
        UserEntity userEntity = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        return userEntity.getBookedTickets();
    }
}
