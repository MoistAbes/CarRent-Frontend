package com.example.rentcarfrontend.views;

import com.example.rentcarfrontend.cars.CarForm;
import com.example.rentcarfrontend.domain.Car;
import com.example.rentcarfrontend.service.CarService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("rent-car")
public class RentCarView extends VerticalLayout {

    private CarService carService = new CarService();
    private Grid<Car> grid = new Grid<>(Car.class);

    private CarForm form = new CarForm(this);





    public RentCarView(){


        grid.setColumns("year", "brand", "model", "type");
        form.setMaxHeight("250");
        VerticalLayout gridLayout = new VerticalLayout(grid );
        VerticalLayout formLayout = new VerticalLayout( form);

        gridLayout.setSizeFull();
        add(gridLayout, formLayout);
        setSizeFull();
        form.setCar(null);


        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> carClicked());
        //grid.asSingleSelect().addValueChangeListener(event -> form.setCar(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(carService.getCars());
    }

    public void carClicked(){
        form.setCar(grid.asSingleSelect().getValue());
        form.setReview(
                grid.asSingleSelect().getValue().getBrand() + " " +
                        grid.asSingleSelect().getValue().getModel() + " " +
                        grid.asSingleSelect().getValue().getYear());
    }



}
