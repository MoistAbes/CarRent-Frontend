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

@Route("register")
public class RegisterView extends VerticalLayout {

    UserClient userClient = new UserClient();
    Button registerButton = new Button("Register");
    Button loginButton = new Button("Already got account? Log in here!");

    Paragraph registerParagraph = new Paragraph("Register");


    TextField firstNameField = new TextField("First name", "", "");
    TextField lastNameField = new TextField("Last name", "", "");
    TextField userNameField = new TextField("Username", "", "");
    PasswordField passwordField = new PasswordField("Password", "");


    public RegisterView(){



        registerButton.addClickListener(e ->{
            if  (userClient.createUser(firstNameField.getValue(), lastNameField.getValue(), userNameField.getValue(), passwordField.getValue())) {
                Notification.show("Registration successful");
                registerButton.getUI().ifPresent(ui ->
                        ui.navigate("login"));
            }else {
                Notification.show("Username is taken");
            }

        });

        loginButton.addClickListener(event -> {
            loginButton.getUI().ifPresent(ui -> {
                ui.navigate("login");
            });
        });


        VerticalLayout loginContent = new VerticalLayout(registerParagraph, firstNameField, lastNameField, userNameField, passwordField, registerButton, loginButton);
        loginContent.setSizeFull();
        loginContent.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
        loginContent.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(loginContent);

    }

}
