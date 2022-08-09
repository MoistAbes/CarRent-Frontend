package com.example.rentcarfrontend.views;

import com.example.rentcarfrontend.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Route("register")
public class RegisterView extends VerticalLayout {

    RestTemplate restTemplate = new RestTemplate();

    Button registerButton = new Button("Register");

    Paragraph registerParagraph = new Paragraph("Register");


    TextField firstNameField = new TextField("First name", "", "");
    TextField lastNameField = new TextField("Last name", "", "");
    TextField userNameField = new TextField("Username", "", "");
    PasswordField passwordField = new PasswordField("Password", "");


    public RegisterView(){


        registerButton.addClickListener(e ->{
            createUser(firstNameField.getValue(), lastNameField.getValue(), userNameField.getValue(), passwordField.getValue());
            //trzeba bedzie dodac weryfikacje poprawnej rejestracji
            //do zmian
            registerButton.getUI().ifPresent(ui ->
                    ui.navigate("dashboard"));
        });


        VerticalLayout loginContent = new VerticalLayout(registerParagraph, firstNameField, lastNameField, userNameField, passwordField, registerButton);
        loginContent.setSizeFull();
        loginContent.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
        loginContent.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(loginContent);

    }

    /*
    testowy button
    public void getUser(){
        String fooResourceUrl
                = "http://localhost:8083/v1/users";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/27", String.class);


        System.out.println("USER: " + response + " :USER");
    }

     */

    public void createUser(String firstname, String surname, String username, String password){
        UserDto userDto = new UserDto(firstname, surname, username, password);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/users")
                .build()
                .encode()
                .toUri();


        restTemplate.postForObject(url, userDto, UserDto.class);

    }




}
