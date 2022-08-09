package com.example.rentcarfrontend.cars;

import com.example.rentcarfrontend.domain.Car;
import com.example.rentcarfrontend.service.CarReviewService;
import com.example.rentcarfrontend.views.RentCarView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CarForm extends FormLayout {

    private RentCarView rentCarView;
    private CarReviewService carReviewService;


    DatePicker rentFrom = new DatePicker("Rent from:");
    DatePicker rentTo = new DatePicker("Rent to:");

    private Button rentButton = new Button("Rent");

    private Label reviewLabel = new Label("Review");
    private Label review = new Label();

    //private Binder<Car> binder = new Binder<Car>(Car.class);


    public CarForm(RentCarView rentCarView){

        carReviewService = new CarReviewService();


        HorizontalLayout buttons = new HorizontalLayout(rentButton);
        VerticalLayout reviewLayout = new VerticalLayout(reviewLabel, review);
        rentButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(rentFrom, rentTo, buttons, reviewLayout);
        //binder.bindInstanceFields(this);
        this.rentCarView = rentCarView;
        rentButton.addClickListener(event -> save());
    }

    private void save() {

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
