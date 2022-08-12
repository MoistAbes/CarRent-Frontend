package com.example.rentcarfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentedCarDto {

    private Long id;
    private int year;
    private String brand;
    private String model;
    private String type;

    public RentedCarDto(int year, String brand, String model, String type) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
    }

    @Override
    public String toString() {
        return "RentedCarDto{" +
                "id=" + id +
                ", year=" + year +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
