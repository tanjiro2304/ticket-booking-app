package com.vn.ticketbookingapp.modules.home;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@UIScope
@SpringComponent
public class HomePresenter extends BasePresenter<HomeView> {
    @Lazy
    @Autowired
    private HomeView homeView;
    public void signUpListener(){
        homeView.getSignUpButton().getUI().ifPresent(ui-> {
            ui.navigate("create-account");
        });
    }

    public void logInListener(){
        homeView.getLoginButton().getUI().ifPresent(ui->{
            ui.navigate("login");
        });
    }
}
