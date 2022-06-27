package com.stackroute.wipro.backend.User;

import com.stackroute.wipro.backend.User.Controller.UserController;
import com.stackroute.wipro.backend.User.model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {



    @InjectMocks
    private  UserController userController;

    private HttpHeaders headers;
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("Register User")
    void test1(){
        UserData userData  = new UserData();
        userData.setUserId(20L);
        userData.setUsername("abc");
        userData.setPassword("password");
        userData.setRole("Role");
        userData.setEmail("abc@gmail.com");
        System.out.println(userData);
//
//        HttpEntity<UserData>  userDataHttpEntity = new HttpEntity<>(userData);
//        final ResponseEntity<Map> responsePOSTEntity = restTemplate.exchange("/user/registerUser/", HttpMethod.POST,
//                userDataHttpEntity, Map.class);
//
//        assertThat(responsePOSTEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);

    }



}