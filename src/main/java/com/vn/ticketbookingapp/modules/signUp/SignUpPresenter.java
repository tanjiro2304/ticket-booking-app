package com.vn.ticketbookingapp.modules.signUp;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.ticketbookingapp.entities.UserEntity;
import com.vn.ticketbookingapp.mvputils.BasePresenter;
import com.vn.ticketbookingapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class SignUpPresenter extends BasePresenter<SignUpView> {
    @Autowired
    UserServiceImpl userService;

    public void createAccount(UserEntity userEntity){
        userService.addUser(userEntity);
    }
}
