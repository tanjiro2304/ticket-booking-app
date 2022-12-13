package com.vn.ticketbookingapp.modules.login;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BaseView;
import lombok.Getter;

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
           loginListener(event.getUsername(),event.getPassword());
           loginForm.setEnabled(true);
        });

        add(div);
    }

    public void loginListener(String username, String password) {
        if (getPresenter().verifyUserNameAndPassword(username, password)) {
            loginForm.getUI().ifPresent(e -> e.navigate("services"));

        } else {
            Notification.show("Invalid Username Or Password", 3000, Notification.Position.TOP_END).
                    addThemeVariants(NotificationVariant.LUMO_ERROR);

        }
    }


}
