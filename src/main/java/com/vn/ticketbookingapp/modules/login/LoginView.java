package com.vn.ticketbookingapp.modules.login;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BaseView;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("/login")
@UIScope
@SpringComponent
@Getter
public class LoginView extends BaseView<LoginPresenter> {
    private LoginForm loginForm;
    private Div div;
    private Div divForButton;

    private Binder<UserEntity> loginBinder;

    @Override
    protected void init() {


        getStyle().set("background-image", "url('images/tejas-rajdhani.jpg')").
                set("background-size", "cover");
        loginForm = new LoginForm();

        setSizeFull();

        div = new Div();

        divForButton = new Div();

        loginBinder = new Binder<>();

        div.add(loginForm);
        div.getStyle().set("margin", "auto");
        loginForm.setForgotPasswordButtonVisible(true);

        loginForm.addLoginListener(event -> {
           getPresenter().loginListener(event.getUsername(),event.getPassword(),loginForm);
           loginForm.setEnabled(true);
        });

        add(div);
    }


}
