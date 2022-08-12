package com.example.rentcarfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    private Long id;
    private Long userId;
    private Long rentedCarId;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public RentDto(Long userId, Long rentedCarId, LocalDate rentFrom, LocalDate rentTo) {
        this.userId = userId;
        this.rentedCarId = rentedCarId;
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    @Override
    public String toString() {
        return "RentDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", rentedCarId=" + rentedCarId +
                ", rentFrom=" + rentFrom +
                ", rentTo=" + rentTo +
                '}';
    }
}
