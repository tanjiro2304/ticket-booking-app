package com.vn.ticketbookingapp.modules.login;


import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class LoginPresenter extends BasePresenter<LoginView> {
    @Autowired
    UserService userServiceImpl;

    public Boolean verifyUserNameAndPassword(String username, String password) {
        return userServiceImpl.ifUserExists(username, password);
    }




}
