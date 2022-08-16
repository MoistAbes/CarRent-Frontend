package com.example.rentcarfrontend.views;

import com.example.rentcarfrontend.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("dashboard")
public class DashboardView extends VerticalLayout {

    private Button rentCarButton = new Button("Rent a car");
    private Button rentsButton = new Button("Rents");
    private Button accountButton = new Button("Account");

    private VaadinSession vaadinSession;
    private HorizontalLayout logoutButtonLayout = new HorizontalLayout();
    private HorizontalLayout dashBoardLayout = new HorizontalLayout();

    private UserDto user;
    private Label welcomeLabel = new Label();

    private Button loginPageButton;
    private Label loginPageLabel;

    private Button logoutButton;

    public DashboardView(){
        vaadinSession = VaadinSession.getCurrent();
        loginPageButton = new Button("Move to login page");
        loginPageLabel = new Label("You are not logged in!");
        logoutButton = new Button("Logout");
        VerticalLayout logInLayout = new VerticalLayout();
        logInLayout.add(loginPageLabel, loginPageButton);

        if (vaadinSession.getAttribute("userId") != null){
            user = (UserDto) vaadinSession.getAttribute("userId");

            logoutButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);


            logoutButtonLayout.add(logoutButton);
            logoutButtonLayout.setJustifyContentMode(JustifyContentMode.END);
            welcomeLabel.setText("Dashboard");
            VerticalLayout dashboardLayout = new VerticalLayout();

            logoutButtonLayout.setSizeFull();

            dashboardLayout.setPadding(true);
            dashboardLayout.setHeightFull();
            dashboardLayout.setSizeFull();
            dashboardLayout.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
            dashboardLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            dashboardLayout.add(welcomeLabel);
            dashboardLayout.add(rentCarButton);
            dashboardLayout.add(rentsButton);
            dashboardLayout.add(accountButton);
            add(logoutButtonLayout,dashboardLayout);
        }else {
            logInLayout.setSizeFull();
            logInLayout.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
            logInLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            add(logInLayout);
        }




        rentCarButton.addClickListener(e ->
            rentCarButton.getUI().ifPresent(ui ->
                    ui.navigate("rent-car"))
        );

        rentsButton.addClickListener(e ->
                rentsButton.getUI().ifPresent(ui ->
                        ui.navigate("rents"))
        );

        accountButton.addClickListener(event -> {
            accountButton.getUI().ifPresent(ui -> {
                ui.navigate("account");
            });
        });

        loginPageButton.addClickListener(event -> {
            loginPageButton.getUI().ifPresent(ui -> {
                ui.navigate("login");
            });
        });

        logoutButton.addClickListener(event -> {
            logoutButton.getUI().ifPresent(ui -> {
                vaadinSession.setAttribute("userId", null);
                ui.navigate("login");
            });
        });

    }

}
