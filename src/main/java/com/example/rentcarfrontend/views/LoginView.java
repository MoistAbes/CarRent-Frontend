package com.example.rentcarfrontend.views;


import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class LoginView extends VerticalLayout {

    LoginForm loginForm = new LoginForm();
    HorizontalLayout labelLayout = new HorizontalLayout();


    public LoginView(){

        labelLayout.add(new Label("Don't have account? Click here!"));
        labelLayout.addClickListener( e ->
                labelLayout.getUI().ifPresent(ui ->
                        ui.navigate("register"))
        );

        VerticalLayout registerContent = new VerticalLayout(loginForm, labelLayout);
        registerContent.setSizeFull();
        registerContent.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
        registerContent.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);


        add(registerContent);

    }
}
