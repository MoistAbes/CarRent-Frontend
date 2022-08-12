package com.example.rentcarfrontend.views;

import com.example.rentcarfrontend.client.UserClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    UserClient userClient = new UserClient();

    Button logInButton = new Button("Log in");
    Button registerButton = new Button("Don't have accout? Register here!");

    Paragraph logInPage = new Paragraph("Log in page");

    TextField userNameField = new TextField("Username", "", "");
    PasswordField passwordField = new PasswordField("Password", "");


    public LoginView(){


        VerticalLayout loginContent = new VerticalLayout(logInPage, userNameField, passwordField,logInButton, registerButton );
        loginContent.setSizeFull();
        loginContent.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
        loginContent.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(loginContent);


        logInButton.addClickListener(e ->{
            if  (userClient.authenticate(userNameField.getValue(), passwordField.getValue())) {
                Notification.show("Log in successful");
                logInButton.getUI().ifPresent(ui ->
                        ui.navigate("dashboard"));
            }else {
                Notification.show("Wrong username or password");
            }

        });

        registerButton.addClickListener(event -> {
            registerButton.getUI().ifPresent(ui -> {
                ui.navigate("register");
            });
        });



    }
}
