package com.vn.ticketbookingapp.modules.login;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.mvputils.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("/login")
@UIScope
@SpringComponent
public class LoginView extends BaseView<LoginPresenter> {

    @Autowired
    private LoginPresenter loginPresenter;

    private LoginForm loginForm;

    private Div div;

    private Div divForButton;

    private User user;



    private Binder<User> loginBinder;

    private VerticalLayout formLayout;

    @Override
    @PostConstruct
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
            if(loginPresenter.verifyUserNameAndPassword(event.getUsername(),event.getPassword())){
                loginForm.getUI().ifPresent(e -> e.navigate("services"));
                loginForm.setEnabled(true);
            }else{
                Notification.show("Invalid Username Or Password",3000, Notification.Position.TOP_END).
                        addThemeVariants(NotificationVariant.LUMO_ERROR);
                loginForm.setEnabled(true);
            }
        });

        add(div);
    }
}
