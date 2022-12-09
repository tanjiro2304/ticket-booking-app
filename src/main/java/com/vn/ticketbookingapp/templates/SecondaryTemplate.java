package com.vn.ticketbookingapp.templates;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.modules.bookinghistory.HistoryView;
import com.vn.ticketbookingapp.modules.booktickets.BookingView;
import com.vn.ticketbookingapp.modules.login.LoginView;
import com.vn.ticketbookingapp.modules.servicemenu.ServiceMenuView;
import com.vn.ticketbookingapp.modules.servicemenu.TimeTableView;

@UIScope
@SpringComponent
public class SecondaryTemplate extends AppLayout {

    private Tabs tabs;
    private H4 title;

    private VerticalLayout content;

    SecondaryTemplate(){
        content = new VerticalLayout();
        setContent(content);
//        getStyle().set("background-color", "#95F887");
//        setSizeFull();
        DrawerToggle toggle = new DrawerToggle();
        title = new H4("Welcome To VRN Railways");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(toggle, title);
        setDrawerOpened(false);

        tabs = new Tabs();
        Tab tab = new Tab("Time Table");
        tabs.add(
//                createTab(VaadinIcon.SEARCH,"Search"),
//                createTab(VaadinIcon.TRAIN,"Premium Services"),
//                createTab(VaadinIcon.RECORDS,"Book History"),
                createTab(VaadinIcon.HOME,"Home", ServiceMenuView.class),
                createTab(VaadinIcon.TABLE,"Time Table", TimeTableView.class),
                createTab(VaadinIcon.TICKET,"Book Tickets", BookingView.class),
                createTab(VaadinIcon.SIGN_OUT,"Log Out", LoginView.class),
                createTab(VaadinIcon.ARCHIVES, "History", HistoryView.class)
                );

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        if(tabs.getSelectedTab().equals(tab)){
            content.add(new TimeTableView());
        }

        addToDrawer(tabs);
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> targetRoute) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(targetRoute);
        link.setTabIndex(-1);
        return new Tab(link);
    }
}
