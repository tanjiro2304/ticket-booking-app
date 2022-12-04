package com.vn.ticketbookingapp.modules.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.modules.signUp.SignUpView;
import com.vn.ticketbookingapp.mvputils.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("/login")
@UIScope
@SpringComponent
public class LoginView extends BaseView<LoginPresenter> {

    @Autowired
    private LoginPresenter loginPresenter;

    private LoginOverlay loginOverlay;

    private Div div;

    private Div divForButton;

    private User user;

    private Button signUpButton;

    private Button loginButton;

    private SignUpView signUpForm;

    private Binder<User> loginBinder;

    private VerticalLayout formLayout;

    @Override
    @PostConstruct
    protected void init() {
        getStyle().set("background-image", "url('images/vande-bharat.webp')").
                set("background-size", "cover");
        setSizeFull();
        loginOverlay = new LoginOverlay();

        div = new Div();

        divForButton = new Div();
        signUpForm = new SignUpView();
        signUpButton = new Button("Sign Up");
        loginButton = new Button("Log In");
        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        signUpButton.addClickListener(click->{
            signUpButton.getUI().ifPresent(ui -> ui.navigate("create-account"));
        });

        loginButton.addClickListener(event->  loginOverlay.setOpened(true));
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        loginBinder = new Binder<>();

        div.add(loginOverlay);
        div.getStyle().set("margin", "auto");
        loginOverlay.setForgotPasswordButtonVisible(true);
        signUpButton.getStyle().set("margin","auto");
        loginButton.getStyle().set("margin","auto");
        formLayout = new VerticalLayout(signUpButton,loginButton);


        formLayout.getStyle().set("background-color", "white").set("margin","auto");
        formLayout.setWidth("30%");
        add(formLayout);
    }
}
