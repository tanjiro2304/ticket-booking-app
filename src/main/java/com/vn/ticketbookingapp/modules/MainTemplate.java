package com.vn.ticketbookingapp.modules;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

public class MainTemplate extends VerticalLayout implements RouterLayout {

    public MainTemplate(){
        getStyle().set("background-image", "url('images/vande-bharat.webp')").
                set("background-size", "cover");

    }
}
