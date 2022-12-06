package com.vn.ticketbookingapp.modules.home;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.templates.MainTemplate;
import com.vn.ticketbookingapp.mvputils.BaseView;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Getter
@UIScope
@SpringComponent
@Route(value = "",layout = MainTemplate.class)
public class HomeView extends BaseView<HomePresenter>  {

    @Autowired
    private HomePresenter homePresenter;
    private Div buttonLayout;

    private Button loginButton;
    private Button signUpButton;

    @Override
    @PostConstruct
    protected void init() {
        loginButton = new Button("Login");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        loginButton.addClickListener(event -> homePresenter.signUpListener());

        signUpButton = new Button("Sign Up");
        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        signUpButton.addClickListener(event -> homePresenter.logInListener());
        buttonLayout =  new Div(loginButton, signUpButton);
        buttonLayout.getStyle().set("margin","auto");
        add(buttonLayout);
    }
}
