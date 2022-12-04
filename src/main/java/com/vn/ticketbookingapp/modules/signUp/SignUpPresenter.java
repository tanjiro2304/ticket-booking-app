package com.vn.ticketbookingapp.modules.signUp;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class SignUpPresenter extends BasePresenter<SignUpView> {
    @Autowired
    UserService userService;

    public void createAccount(User user){
        userService.addUser(user);
    }
}
