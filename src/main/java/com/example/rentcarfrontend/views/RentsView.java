package com.example.rentcarfrontend.views;

import com.example.rentcarfrontend.client.RentClient;
import com.example.rentcarfrontend.client.RentedCarClient;
import com.example.rentcarfrontend.domain.RentListElement;
import com.example.rentcarfrontend.dto.RentDto;
import com.example.rentcarfrontend.dto.RentedCarDto;
import com.example.rentcarfrontend.dto.UserDto;
import com.example.rentcarfrontend.form.RentForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Route("rents")
public class RentsView extends VerticalLayout {

    private Label titleLabel;
    private VaadinSession vaadinSession;


    private RentClient rentClient = new RentClient();
    private RentedCarClient rentedCarClient = new RentedCarClient();

    private Grid<RentListElement> grid = new Grid<>(RentListElement.class);
    private RentForm form = new RentForm(this);

    private UserDto userInfo;

    private Button loginPageButton;
    private Label loginPageLabel;


    public RentsView(){
        vaadinSession = VaadinSession.getCurrent();

        loginPageButton = new Button("Move to login page");
        loginPageLabel = new Label("You are not logged in!");
        VerticalLayout logInLayout = new VerticalLayout();
        logInLayout.add(loginPageLabel, loginPageButton);
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();


        if (vaadinSession.getAttribute("userId") != null){
            userInfo = (UserDto) vaadinSession.getAttribute("userId");
            titleLabel = new Label("Your rents");
            HorizontalLayout titleLayout = new HorizontalLayout(titleLabel);

            grid.setColumns("year", "brand", "model", "type", "rentFrom", "rentTo");

            add(titleLayout, mainContent);
            setSizeFull();
            form.setRentListElement(null);

            refresh();

            grid.asSingleSelect().addValueChangeListener(event -> {
                form.setRentListElement(grid.asSingleSelect().getValue());
                vaadinSession.setAttribute("rent", new RentDto(
                        grid.asSingleSelect().getValue().getRentId(),
                        grid.asSingleSelect().getValue().getUserId(),
                        grid.asSingleSelect().getValue().getRentedCarId(),
                        grid.asSingleSelect().getValue().getRentFrom(),
                        grid.asSingleSelect().getValue().getRentTo()
                ));
            });


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

        List<RentDto> responseRentList = rentClient.getUserRents(userInfo.getId());

        List<RentedCarDto> rentedCarList = responseRentList.stream()
                .map(rentDto -> rentedCarClient.getRentedCar(rentDto.getRentedCarId()))
                .collect(Collectors.toList());

        List<RentListElement> rentListElements = createRentListElements(responseRentList, rentedCarList);
        for (RentListElement rentListElement : rentListElements){
            System.out.println(rentListElement);
        }


        grid.setItems(rentListElements);

    }

    public List<RentListElement> createRentListElements(List<RentDto> rents, List<RentedCarDto> rentedCars){

        Map<RentDto, RentedCarDto> rentsAndRenterCarsMap = new HashMap<>();
        List<RentListElement> resultList = new ArrayList<>();

        for (int i = 0; i < rents.size(); i++){
            rentsAndRenterCarsMap.put(rents.get(i), rentedCars.get(i));
        }

        for (Map.Entry<RentDto, RentedCarDto> entry : rentsAndRenterCarsMap.entrySet()) {

            RentListElement rentListElement = new RentListElement.RentListElementBuilder()
                    .rentId(entry.getKey().getId())
                    .userId(entry.getKey().getUserId())
                    .rentedCarId( entry.getKey().getRentedCarId())
                    .year(entry.getValue().getYear())
                    .brand(entry.getValue().getBrand())
                    .model(entry.getValue().getModel())
                    .type(entry.getValue().getType())
                    .rentFrom(entry.getKey().getRentFrom())
                    .rentTo(entry.getKey().getRentTo())
                    .build();

            resultList.add(rentListElement);
        }
        return resultList;
    }


}
