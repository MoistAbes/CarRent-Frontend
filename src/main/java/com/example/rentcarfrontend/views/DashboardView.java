package com.example.rentcarfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("dashboard")
public class DashboardView extends VerticalLayout {

    private Button rentCarButton = new Button("Rent a car");

    public DashboardView(){
        HorizontalLayout dashboardLayout = new HorizontalLayout();
        dashboardLayout.setPadding(true);
        dashboardLayout.setHeightFull();
        dashboardLayout.setSizeFull();
        dashboardLayout.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
        dashboardLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        dashboardLayout.add(rentCarButton);
        dashboardLayout.add(new Button("Rents"));
        dashboardLayout.add(new Button("Account"));


        rentCarButton.addClickListener(e ->
            rentCarButton.getUI().ifPresent(ui ->
                    ui.navigate("rent-car"))
        );

        add(dashboardLayout);


    }

}
