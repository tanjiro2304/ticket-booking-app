package com.vn.ticketbookingapp.modules.login;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.service.UserService;
import com.vn.ticketbookingapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@UIScope
@SpringComponent
public class LoginPresenter extends BasePresenter<LoginView> {
    @Autowired
    UserService userServiceImpl;

    @Lazy
    @Autowired
    LoginView loginView;

    public Boolean verifyUserNameAndPassword(String username, String password) {
        return userServiceImpl.ifUserExists(username, password);
    }

    public void loginListener(String username, String password) {
        if (verifyUserNameAndPassword(username, password)) {
            loginView.getLoginForm().getUI().ifPresent(e -> e.navigate("services"));
            loginView.getLoginForm().setEnabled(true);
        } else {
            Notification.show("Invalid Username Or Password", 3000, Notification.Position.TOP_END).
                    addThemeVariants(NotificationVariant.LUMO_ERROR);
            loginView.getLoginForm().setEnabled(true);
        }
    }


}
