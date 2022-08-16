package com.example.rentcarfrontend.service;

import com.example.rentcarfrontend.domain.Car;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@NoArgsConstructor
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Car> getCars(){

        try {
            Car[] carsResponse =
                    restTemplate.getForObject("http://localhost:8083/v1/cars", Car[].class);
            return new ArrayList<>(Optional.ofNullable(carsResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

    }



}
