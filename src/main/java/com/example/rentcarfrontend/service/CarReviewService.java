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
public class CarReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public String getReview(String search){

        System.out.println(search);

        try {
            String carReviewResponse =
                    restTemplate.getForObject("http://localhost:8083/v1/review/" + search + " review", String.class);
            return Optional.ofNullable(carReviewResponse)
                    .orElse("");
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return "";
        }

    }

}
