package com.example.rentcarfrontend.client;

import com.example.rentcarfrontend.dto.RentedCarDto;
import com.example.rentcarfrontend.dto.UserDto;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@NoArgsConstructor
public class RentedCarClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentedCarClient.class);

    private final RestTemplate restTemplate = new RestTemplate();


    public Long createRentedCar(int year, String brand, String model, String type){
        RentedCarDto rentedCarDto = new RentedCarDto(year, brand, model, type);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/rentedCars")
                .build()
                .encode()
                .toUri();

        Long responseRentedCarDtoId = restTemplate.postForObject(url, rentedCarDto, Long.class);
        //rented car id
        System.out.println(responseRentedCarDtoId);

        return responseRentedCarDtoId;

    }

    public RentedCarDto getRentedCar(Long rentedCarId){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/rentedCars/" + rentedCarId)
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(url, RentedCarDto.class);
    }

}
