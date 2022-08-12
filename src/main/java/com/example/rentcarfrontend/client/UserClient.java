package com.example.rentcarfrontend.client;

import com.example.rentcarfrontend.dto.UserDto;
import com.example.rentcarfrontend.service.CarService;
import com.vaadin.flow.server.VaadinSession;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
@NoArgsConstructor
public class UserClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);

    private final RestTemplate restTemplate = new RestTemplate();

    private VaadinSession vaadinSession = VaadinSession.getCurrent();

    public boolean createUser(String firstname, String surname, String username, String password){
        UserDto userDto = new UserDto(firstname, surname, username, password);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/users")
                .build()
                .encode()
                .toUri();


        try {
            restTemplate.postForObject(url, userDto, UserDto.class);
            return true;
        }catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean authenticate(String username, String password){

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/v1/users/" + username + "/" + password)
                .build()
                .encode()
                .toUri();


            Optional<UserDto> responseUser = Optional.ofNullable(restTemplate.getForObject(url, UserDto.class));

            if (responseUser.isPresent()){
                if (responseUser.get().getName() != null){
                    vaadinSession.setAttribute("userId", responseUser.get());
                    System.out.println(responseUser);

                    return true;

                }else {
                    return false;
                }
            }

            return false;

    }

}
