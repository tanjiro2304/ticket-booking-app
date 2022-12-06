package com.vn.ticketbookingapp.templates;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;

public class SecondaryTemplate extends AppLayout {

    private Tabs tabs;
    private H4 title;

    SecondaryTemplate(){
//        getStyle().set("background-color", "#95F887");
//        setSizeFull();
        DrawerToggle toggle = new DrawerToggle();
        title = new H4("Welcome To VRN Railways");
        addToNavbar(toggle, title);
        setDrawerOpened(false);
        tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.SEARCH,"Search"),
                createTab(VaadinIcon.TRAIN,"Premium Services"),
                createTab(VaadinIcon.RECORDS,"Book History")
                );
    }

    private Tab createTab(VaadinIcon search, String search1) {
//        Icon icon = viewIcon.crea
        return new Tab();
    }
}
