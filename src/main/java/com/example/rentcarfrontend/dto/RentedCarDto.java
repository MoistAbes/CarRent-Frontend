package com.example.rentcarfrontend.dto;

import com.example.rentcarfrontend.domain.RentedCarStatus;
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
    private RentedCarStatus status;

    public RentedCarDto(int year, String brand, String model, String type) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
    }

    public RentedCarDto(int year, String brand, String model, String type, RentedCarStatus status) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentedCarDto{" +
                "id=" + id +
                ", year=" + year +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                '}';
    }
}
