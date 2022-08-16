package com.example.rentcarfrontend.service;


import com.example.rentcarfrontend.domain.Car;
import com.example.rentcarfrontend.domain.WebSearchEditor;
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

    private WebSearchEditor webSearchEditor;

    public String getReview(String search){

        System.out.println(search);
        webSearchEditor = WebSearchEditor.INSTANCE;

        try {
            String carReviewResponse =
                    restTemplate.getForObject("http://localhost:8083/v1/review/" + search + " review", String.class);

            String result = Optional.ofNullable(carReviewResponse)
                    .orElse("");

            return webSearchEditor.editBody(result);

        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return "";
        }

    }

}
