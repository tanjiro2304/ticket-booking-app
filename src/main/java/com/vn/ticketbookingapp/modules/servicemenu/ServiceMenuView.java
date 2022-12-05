package com.vn.ticketbookingapp.modules.servicemenu;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.templates.MainTemplate;
import com.vn.ticketbookingapp.mvputils.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
@Route(value = "services", layout = MainTemplate.class)
public class ServiceMenuView extends BaseView<ServiceMenuPresenter> {

    @Autowired
    ServiceMenuPresenter serviceMenuPresenter;

    private HorizontalLayout firstLayout;



    @Override
    @PostConstruct
    protected void init() {
        H1 title = new H1("Online Reservation System(ORS)");
        title.getStyle().set("margin","auto").
                set("padding-bottom","2%").set("padding-top","2%");
        User user = (User) VaadinSession.getCurrent().getAttribute("user");
        H4 greetUser= new H4("Welcome " + user.getFirstName() +
                " " + user.getLastName());


        greetUser.getStyle().set("margin","auto").set("padding","auto");

        VerticalLayout verticalLayout = new VerticalLayout(title,greetUser);

        Div div = new Div(verticalLayout);
        div.getStyle().set("background-color","white");
        verticalLayout.getStyle().set("margin","auto");
        setHorizontalLayout();
        div.setWidthFull();
        firstLayout.setSizeFull();
        add(div,firstLayout);
    }

    private void setHorizontalLayout(){
        firstLayout = new HorizontalLayout();

        Image firstImage = new Image("images/Vande_Bharat_Train_2.webp","No Image Found");
        firstImage.setHeight("45%");
        firstImage.setWidth("45%");
        firstImage.setTitle("Vande Bharat Express");
    }
}
