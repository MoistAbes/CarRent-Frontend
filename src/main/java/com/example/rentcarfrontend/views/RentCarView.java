package com.example.rentcarfrontend.views;

import com.example.rentcarfrontend.cars.CarForm;
import com.example.rentcarfrontend.domain.Car;
import com.example.rentcarfrontend.dto.UserDto;
import com.example.rentcarfrontend.service.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("rent-car")
public class RentCarView extends VerticalLayout {

    private VaadinSession vaadinSession;
    private UserDto userInfo;

    private CarService carService = new CarService();
    private Grid<Car> grid = new Grid<>(Car.class);

    private CarForm form = new CarForm(this);

    private Button loginPageButton;
    private Label loginPageLabel;

    public RentCarView(){

        vaadinSession = VaadinSession.getCurrent();

        loginPageButton = new Button("Move to login page");
        loginPageLabel = new Label("You are not logged in!");
        VerticalLayout logInLayout = new VerticalLayout();
        logInLayout.add(loginPageLabel, loginPageButton);

        if (vaadinSession.getAttribute("userId") != null){
            grid.setColumns("year", "brand", "model", "type");
            VerticalLayout gridLayout = new VerticalLayout(grid);
            VerticalLayout formLayout = new VerticalLayout(form);

            gridLayout.setSizeFull();
            add(gridLayout, formLayout);
            setSizeFull();
            form.setCar(null);

            refresh();

            grid.asSingleSelect().addValueChangeListener(event -> carClicked());
        }else {
            logInLayout.setSizeFull();
            logInLayout.setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );
            logInLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
            add(logInLayout);
        }

        loginPageButton.addClickListener(event -> {
            loginPageButton.getUI().ifPresent(ui -> {
                ui.navigate("login");
            });
        });


    }

    public void refresh() {
        grid.setItems(carService.getCars());
    }

    public void carClicked(){
        vaadinSession.setAttribute("rentedCar", grid.asSingleSelect().getValue());
        form.setCar(grid.asSingleSelect().getValue());
        form.setReview(
                grid.asSingleSelect().getValue().getBrand() + " " +
                grid.asSingleSelect().getValue().getModel() + " " +
                grid.asSingleSelect().getValue().getYear());
    }
}
