package com.example.rentcarfrontend.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class RentListElement {

    private int year;
    private String brand;
    private String model;
    private String type;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public LocalDate getRentFrom() {
        return rentFrom;
    }

    public LocalDate getRentTo() {
        return rentTo;
    }
}
