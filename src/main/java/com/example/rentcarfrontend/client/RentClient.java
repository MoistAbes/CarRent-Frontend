package com.example.rentcarfrontend.client;

import com.example.rentcarfrontend.domain.RentListElement;
import com.example.rentcarfrontend.dto.RentDto;
import com.example.rentcarfrontend.dto.RentedCarDto;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class RentClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentedCarClient.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public void createRent(Long userId, Long rentedCarId, LocalDate rentFrom, LocalDate rentTo){
        RentDto rentDto = new RentDto(userId, rentedCarId, rentFrom, rentTo);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/rents")
                .build()
                .encode()
                .toUri();

        restTemplate.postForObject(url, rentDto, RentDto.class);
    }

    public List<RentDto> getUserRents(Long userId){

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/rents/userRents/" + userId)
                .build()
                .encode()
                .toUri();

        RentDto[] responseRentDto = restTemplate.getForObject(url, RentDto[].class);

        return new ArrayList<>(Optional.ofNullable(responseRentDto)
                .map(Arrays::asList)
                .orElse(Collections.emptyList()));

    }

    public void updateRent(RentDto rentDto){

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/rents")
                .build()
                .encode()
                .toUri();

        HttpEntity<RentDto> requestEntity = new HttpEntity<>(rentDto);

        HttpEntity<RentDto> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, RentDto.class);
    }

    public void deleteRent(Long rentId){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/rents/" + rentId)
                .build()
                .encode()
                .toUri();

        HttpEntity<Long> requestEntity = new HttpEntity<>(rentId);

        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, RentDto.class);
    }
}
