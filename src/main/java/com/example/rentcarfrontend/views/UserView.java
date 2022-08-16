package com.example.rentcarfrontend.views;

import com.example.rentcarfrontend.client.UserClient;
import com.example.rentcarfrontend.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("account")
public class UserView extends VerticalLayout {

    VaadinSession session;
    UserClient userClient = new UserClient();

    private Button loginPageButton;
    private Label loginPageLabel;

    private Label accountInfoLabel;
    private TextField firstnameLabel;
    private TextField lastnameLabel;
    private TextField usernameTextField;

    private Button updateButton;


    public UserView(){
        session = VaadinSession.getCurrent();
        loginPageButton = new Button();
        updateButton = new Button("Update");
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        if (session.getAttribute("userId") != null){
            UserDto userInfo = (UserDto) session.getAttribute("userId");

            accountInfoLabel = new Label("Account information");
            firstnameLabel = new TextField("Firstname",userInfo.getName(), "");
            lastnameLabel = new TextField("Lastname" , userInfo.getSurname(), "");
            usernameTextField = new TextField("Username", userInfo.getUsername(), "");


            VerticalLayout userFullNameLayout = new VerticalLayout();
            userFullNameLayout.add(accountInfoLabel ,firstnameLabel, lastnameLabel, usernameTextField, updateButton);
            userFullNameLayout.setSizeFull();
            userFullNameLayout.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
            userFullNameLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

            add(userFullNameLayout);
        }else {
            loginPageButton = new Button("Move to login page");
            loginPageLabel = new Label("You are not logged in!");
            VerticalLayout logInLayout = new VerticalLayout();
            logInLayout.add(loginPageLabel, loginPageButton);
            logInLayout.setSizeFull();
            logInLayout.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
            logInLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            add(logInLayout);

        }

        updateButton.addClickListener(event -> {
            UserDto userDto = (UserDto) session.getAttribute("userId");

            UserDto updatedUserDto = new UserDto(
                    userDto.getId(),
                    firstnameLabel.getValue(),
                    lastnameLabel.getValue(),
                    usernameTextField.getValue(),
                    userDto.getPassword()
            );

            userClient.updateUser(updatedUserDto);

            session.setAttribute("userId", updatedUserDto);
            Notification.show("User updated");
        });

        loginPageButton.addClickListener(event -> {
            loginPageButton.getUI().ifPresent(ui -> {
                ui.navigate("login");
            });
        });

    }
}
