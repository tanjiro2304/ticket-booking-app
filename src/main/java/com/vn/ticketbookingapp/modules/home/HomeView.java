package com.vn.ticketbookingapp.modules.home;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vn.ticketbookingapp.modules.MainTemplate;
import com.vn.ticketbookingapp.mvputils.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "",layout = MainTemplate.class)
public class HomeView extends BaseView<HomePresenter>  {

    @Autowired
    private HomePresenter homePresenter;
    private VerticalLayout buttonLayout;

    private Button loginButton;
    private Button signUpButton;

    @Override
    @PostConstruct
    protected void init() {
        loginButton = new Button("Login");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        signUpButton = new Button("Sign Up");
        buttonLayout =  new VerticalLayout(loginButton, signUpButton);

        add(buttonLayout);
    }
}
