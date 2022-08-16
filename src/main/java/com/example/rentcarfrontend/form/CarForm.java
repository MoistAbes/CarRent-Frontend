package com.example.rentcarfrontend.form;

import com.example.rentcarfrontend.client.RentClient;
import com.example.rentcarfrontend.client.RentedCarClient;
import com.example.rentcarfrontend.domain.Car;
import com.example.rentcarfrontend.dto.UserDto;
import com.example.rentcarfrontend.service.CarReviewService;
import com.example.rentcarfrontend.views.RentCarView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class CarForm extends FormLayout {

    private VaadinSession vaadinSession = VaadinSession.getCurrent();
    private UserDto userDto;

    private RentCarView rentCarView;
    private CarReviewService carReviewService;
    private RentedCarClient rentedCarClient;
    private RentClient rentClient;


    DatePicker rentFrom = new DatePicker("Rent from:");
    DatePicker rentTo = new DatePicker("Rent to:");

    private Button rentButton = new Button("Rent");

    private Label reviewLabel = new Label("Review");
    private Label review = new Label();



    public CarForm(RentCarView rentCarView){

        userDto = (UserDto) vaadinSession.getAttribute("userId");

        carReviewService = new CarReviewService();
        rentedCarClient = new RentedCarClient();
        rentClient = new RentClient();


        HorizontalLayout buttons = new HorizontalLayout(rentButton);
        VerticalLayout reviewLayout = new VerticalLayout(reviewLabel, review);
        rentButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(rentFrom, rentTo, buttons, reviewLayout);
        this.rentCarView = rentCarView;
        rentButton.addClickListener(event -> {
            saveRent((Car) vaadinSession.getAttribute("rentedCar"));
            Notification.show("Successfully rented car");
        });
    }

    private void saveRent(Car car) {
        Long rentedCarId = rentedCarClient.createRentedCar(car.getYear(), car.getBrand(), car.getModel(), car.getType());

        rentClient.createRent(
                userDto.getId(),
                rentedCarId,
                rentFrom.getValue(),
                rentTo.getValue());
    }

    public void setReview(String search){
        review.setText(carReviewService.getReview(search));
    }


    public void setCar(Car car) {
        //binder.setBean(car);

        if (car == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

}
