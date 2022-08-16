package com.example.rentcarfrontend.form;

import com.example.rentcarfrontend.client.RentClient;
import com.example.rentcarfrontend.client.RentedCarClient;
import com.example.rentcarfrontend.domain.RentListElement;
import com.example.rentcarfrontend.domain.RentedCarStatus;
import com.example.rentcarfrontend.dto.RentDto;
import com.example.rentcarfrontend.dto.RentedCarDto;
import com.example.rentcarfrontend.views.RentsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinSession;

public class RentForm extends FormLayout {

    private RentsView rentsView;

    private VaadinSession vaadinSession;
    private RentClient rentClient = new RentClient();
    private RentedCarClient rentedCarClient = new RentedCarClient();

    private DatePicker rentFrom = new DatePicker("Rent from");
    private DatePicker rentTo = new DatePicker("Rent to");

    private Button save = new Button("Update rent");
    private Button delete = new Button("Cancel rent");

    //private Binder<RentListElement> binder = new Binder<RentListElement>(RentListElement.class);

    public RentForm(RentsView rentsView){

        vaadinSession = VaadinSession.getCurrent();

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        rentFrom.setEnabled(true);

        add(rentFrom, rentTo, buttons);
        //binder.bindInstanceFields(this);
        this.rentsView = rentsView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    private void save() {
        RentDto clickedRentListElement = (RentDto) vaadinSession.getAttribute("rent");
        RentDto updatedRentDto = new RentDto(
                clickedRentListElement.getId(),
                clickedRentListElement.getUserId(),
                clickedRentListElement.getRentedCarId(),
                rentFrom.getValue(),
                rentTo.getValue()
        );
        rentClient.updateRent(updatedRentDto);
        Notification.show("Rent updated");
    }

    private void delete(){
        RentDto clickedRentListElement = (RentDto) vaadinSession.getAttribute("rent");

        rentClient.deleteRent(clickedRentListElement.getId());

        RentedCarDto rentedCarDto = rentedCarClient.getRentedCar(clickedRentListElement.getRentedCarId());

        rentedCarClient.updateRentedCarStatus(new RentedCarDto(
                rentedCarDto.getId(),
                rentedCarDto.getYear(),
                rentedCarDto.getBrand(),
                rentedCarDto.getModel(),
                rentedCarDto.getType(),
                RentedCarStatus.NOT_RENTED
        ));
        Notification.show("Rent canceled");
    }

    public void setRentListElement(RentListElement rentListElement) {
        //binder.setBean(rentListElement);

        if (rentListElement == null) {
            setVisible(false);
        } else {
            setVisible(true);
            rentFrom.focus();
        }
    }


}
