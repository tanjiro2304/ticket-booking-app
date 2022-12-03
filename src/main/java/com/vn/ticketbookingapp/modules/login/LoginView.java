package com.vn.ticketbookingapp.modules.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.mvputils.BaseView;

import javax.annotation.PostConstruct;

@Route("/login")
@UIScope
@SpringComponent
public class LoginView extends BaseView<LoginPresenter> {

    private LoginForm loginForm;

    private Div div ;

    private Div divForButton;

    private User user;

    private Button signUpButton;

    private SignUpForm signUpForm;

    private Binder<User> loginBinder;


    @Override
    @PostConstruct
    protected void init() {
        getStyle().set("background-image","url('images/vande-bharat.webp')").
                set("background-size","cover");
        setSizeFull();
        loginForm = new LoginForm();
        div = new Div();

        divForButton = new Div();
        signUpForm = new SignUpForm();
        signUpButton = new Button("Sign Up");
//
        signUpButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        signUpButton.addClickListener(event -> signUpForm.open());

        loginBinder = new Binder<>();
//        username = new TextField("Username");
//        password = new PasswordField("Password");
//        login = new Button("Login");
        loginForm.setForgotPasswordButtonVisible(true);

        div.add(loginForm);
        divForButton.add(signUpButton);
        div.getStyle().set("margin","auto");

        add(div);

        loginForm.addLoginListener(event -> {

        });


    }
}
