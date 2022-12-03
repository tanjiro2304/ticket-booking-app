package com.vn.ticketbookingapp.modules.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class SignUpForm extends Dialog {

    private FormLayout signUpForm;

    private TextField userId;

    private ComboBox<String> idType;

    private TextField username;

    private PasswordField password;

    private PasswordField confirmPassword;

    private TextField contactNo;

    private Button signUpButton;

    private Button cancelButton;

    private HorizontalLayout buttonLayout;

    private EmailField emailId;

    public SignUpForm(){
        userId = new TextField("ID Card No");

        idType = new ComboBox<>();
        idType.setItems("Aadhar", "PAN", "Voter Id","Driving License");

        username = new TextField("Username");
        password = new PasswordField("Password");
        confirmPassword = new PasswordField("Confirm Password");
        contactNo = new TextField("Contact No");
        signUpButton = new Button("Sign Up");
        cancelButton = new Button("Cancel");
        buttonLayout = new HorizontalLayout(signUpButton, cancelButton);

        emailId = new EmailField("Email Id");

        signUpForm = new FormLayout(username, password, confirmPassword,userId, idType, contactNo, emailId, buttonLayout);
        add(signUpForm);
    }
}
