package com.vn.ticketbookingapp.modules.servicemenu;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
@Route(value = "services", layout = SecondaryTemplate.class)
public class ServiceMenuView extends BaseView<ServiceMenuPresenter> {

    @Autowired
    ServiceMenuPresenter serviceMenuPresenter;

    private HorizontalLayout firstLayout;

    private VerticalLayout searchLayout;



    @Override
    protected void init() {

        H1 title = new H1("Online Reservation System(ORS)");
        title.getStyle().set("margin","auto").
                set("padding-bottom","2%").set("padding-top","2%");

        UserEntity userEntity = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        H4 greetUser= new H4("Welcome " + userEntity.getFirstName() +
                " " + userEntity.getLastName());


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

        Image vandeBharat = new Image("images/Vande_Bharat_Train_2.webp","No Image Found");
        vandeBharat.setHeight("85%");
        vandeBharat.setWidth("45%");
        vandeBharat.setTitle("Vande Bharat Express");
        vandeBharat.addClickListener(event -> {

        });
        firstLayout.add(vandeBharat);
    }
}
