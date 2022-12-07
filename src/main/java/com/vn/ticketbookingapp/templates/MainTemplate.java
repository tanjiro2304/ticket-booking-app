package com.vn.ticketbookingapp.templates;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;


//@StyleSheet("vaadin://style.css")
public class MainTemplate extends VerticalLayout implements RouterLayout {

    public MainTemplate(){

        getStyle().set("background-image", "url('images/vande-bharat.webp')").
                set("background-size", "cover");
        setSizeFull();
    }
}
