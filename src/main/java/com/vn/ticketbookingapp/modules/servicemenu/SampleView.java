package com.vn.ticketbookingapp.modules.servicemenu;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.TransportService;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.mvputils.Presenter;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@UIScope
@SpringComponent
@Route(value = "/sample")
public class SampleView extends AppLayout {

    private VerticalLayout content;

    private HorizontalLayout navbar;

    private VerticalLayout mainLayout;

    Tab timeTable;

    Tabs tabs;

    public SampleView() {
        mainLayout = new VerticalLayout();
        content = new VerticalLayout();
        navbar = new HorizontalLayout();

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyApp");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        tabs = getTabs();

        addToDrawer(tabs);
        navbar.add(toggle,title);
        mainLayout.add(navbar);
        addToNavbar(mainLayout);

        setPrimarySection(Section.DRAWER);
        tabs.addSelectedChangeListener(event->{
            if (tabs.getSelectedTab().equals(timeTable)) {
                content.add(new H1("Hello World"));

            }
        });

        setContent(content);


    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        Tab timeTable = createTab(VaadinIcon.TRAIN, "Train Time Table");
        tabs.add(

                timeTable
        );

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        // Demo has no routes
        // link.setRoute(viewClass.java);
        link.setTabIndex(-1);

        return new Tab(link);
    }


}
