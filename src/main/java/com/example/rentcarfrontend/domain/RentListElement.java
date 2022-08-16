package com.example.rentcarfrontend.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RentListElement {

    //na testa
    private Long rentId;
    private Long userId;
    private Long rentedCarId;
    private int year;
    private String brand;
    private String model;
    private String type;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public static class RentListElementBuilder{
        private Long rentId;
        private Long userId;
        private Long rentedCarId;
        private int year;
        private String brand;
        private String model;
        private String type;
        private LocalDate rentFrom;
        private LocalDate rentTo;

        public RentListElementBuilder rentId(Long rentId){
            this.rentId = rentId;
            return this;
        }
        public RentListElementBuilder userId(Long userId){
            this.userId = userId;
            return this;
        }
        public RentListElementBuilder rentedCarId(Long rentedCarId){
            this.rentedCarId = rentedCarId;
            return this;
        }
        public RentListElementBuilder year(int year){
            this.year = year;
            return this;
        }
        public RentListElementBuilder brand(String brand){
            this.brand = brand;
            return this;
        }
        public RentListElementBuilder model(String model){
            this.model = model;
            return this;
        }
        public RentListElementBuilder type(String type){
            this.type = type;
            return this;
        }
        public RentListElementBuilder rentFrom(LocalDate rentFrom){
            this.rentFrom = rentFrom;
            return this;
        }
        public RentListElementBuilder rentTo(LocalDate rentTo){
            this.rentTo = rentTo;
            return this;
        }

        public RentListElement build(){
            return new RentListElement(
                    rentId,
                    userId,
                    rentedCarId,
                    year,
                    brand,
                    model,
                    type,
                    rentFrom,
                    rentTo
            );
        }
    }

    private RentListElement(
            final Long rentId,
            final Long userId,
            Long rentedCarId,
            int year,
            String brand,
            String model,
            String type,
            LocalDate rentFrom,
            LocalDate rentTo
    ){
       this.rentId = rentId;
       this.userId = userId;
       this.rentedCarId = rentedCarId;
       this.year = year;
       this.brand = brand;
       this.model = model;
       this.type = type;
       this.rentFrom = rentFrom;
       this.rentTo = rentTo;
    }

    public Long getRentId() {
        return rentId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRentedCarId() {
        return rentedCarId;
    }

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

    @Override
    public String toString() {
        return "RentListElement{" +
                "rentId=" + rentId +
                ", userId=" + userId +
                ", rentedCarId=" + rentedCarId +
                ", year=" + year +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", rentFrom=" + rentFrom +
                ", rentTo=" + rentTo +
                '}';
    }
}
