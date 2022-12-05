package com.vn.ticketbookingapp.modules.signUp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vn.ticketbookingapp.entities.User;
import com.vn.ticketbookingapp.modules.login.LoginPresenter;
import com.vn.ticketbookingapp.mvputils.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@Route("/create-account")
public class SignUpView extends BaseView<SignUpPresenter> {

    @Autowired
    private SignUpPresenter signUpPresenter;

    private FormLayout signUpForm;

    private H4 title;

    private Div signUpDiv;

    private TextField userId;

    private ComboBox<String> idType;

    private TextField username;

    private PasswordField password;

    private PasswordField confirmPassword;

    private TextField contactNo;

    private TextField firstName;

    private TextField lastName;


    private Button signUpButton;

    private Button cancelButton;

    private HorizontalLayout buttonLayout;

    private EmailField emailId;

    private Binder<User> userBinder;

    private User user;

    @Override
    @PostConstruct
    protected void init() {
        getStyle().set("background-image", "url('images/vande-bharat.webp')").
                set("background-repeat","no-repeat").
                set("background-position","center").
                set("height","100%").
                set("background-size","cover")  ;
        setSizeFull();
        userId = new TextField("ID Card No");

        idType = new ComboBox<>("Id Card");
        idType.setItems("Aadhar", "PAN", "Voter Id","Driving License");

        H4 title = new H4("Create A New Account");
        username = new TextField("Username");
        password = new PasswordField("Password");
        confirmPassword = new PasswordField("Confirm Password");
        firstName = new TextField("First Name");
        lastName = new TextField("Last Name");
        contactNo = new TextField("Contact No");
        signUpButton = new Button("Sign Up");
        cancelButton = new Button("Cancel");
        emailId = new EmailField("Email Id");
        signUpButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonLayout = new HorizontalLayout(signUpButton, cancelButton);
        signUpForm = new FormLayout(title,firstName, lastName, username, password, confirmPassword, idType,
                userId, contactNo, emailId, buttonLayout);
        setUserBinder();
        signUpButton.addClickListener(click ->{
            user = new User();
            try {
                userBinder.writeBean(user);
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
            signUpPresenter.createAccount(user);
            Notification.show("Account Created Successfully",2000, Notification.Position.TOP_END).
                    addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            signUpButton.getUI().ifPresent(ui -> ui.navigate("login"));

        });

        signUpForm.setColspan(title,2);
        signUpForm.setColspan(username,2);
        signUpForm.setColspan(buttonLayout,2);
        signUpForm.setWidth("55%");
        signUpForm.getStyle().set("margin","auto");


        signUpDiv = new Div();
        signUpDiv.getStyle().set("background-color","white").
        set("margin","auto").set("margin-top","5%").set("margin-bottom","5%");
        signUpDiv.add(signUpForm);
        signUpDiv.setWidth("35%");
        signUpDiv.setHeight("150%");
        add(signUpDiv);
    }

    public void setUserBinder(){
        userBinder = new Binder<>();

        userBinder.forField(firstName).withNullRepresentation("").bind(User::getFirstName, User::setFirstName);
        userBinder.forField(lastName).withNullRepresentation("").bind(User::getLastName,User::setLastName);
        userBinder.forField(username).withNullRepresentation("").bind(User::getUserName,User::setUserName);
        userBinder.forField(password).withNullRepresentation("").bind(User::getPassword,User::setPassword);
        userBinder.forField(confirmPassword).withNullRepresentation("").
                withValidator(pass -> pass.equals(password.getValue()),"Password does not match...");
        userBinder.forField(idType).withNullRepresentation("").bind(User::getIdType,User::setIdType);
        userBinder.forField(userId).withNullRepresentation("").bind(User::getUserId,User::setUserId);
        userBinder.forField(contactNo).withNullRepresentation("").bind(User::getContactNo,User::setContactNo);
        userBinder.forField(emailId).withNullRepresentation("").bind(User::getEmailId, User::setEmailId);
    }

    public User getUser() {
        return user;
    }


}
