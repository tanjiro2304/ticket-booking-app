package com.vn.ticketbookingapp.modules.bookinghistory;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.Passenger;
import com.vn.ticketbookingapp.entities.Tickets;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.service.TicketService;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@UIScope
@SpringComponent
@Route(value="/history", layout = SecondaryTemplate.class)
public class HistoryView extends BaseView<HistoryPresenter> {

    @Autowired
    private TicketHistoryGrid ticketGrid;

    private Tickets ticket;

    @Override
    protected void init() {
        add(ticketGrid);
    }
}
