package com.visual.status.farmvest.services;

import com.visual.status.farmvest.data.models.Roles;
import com.visual.status.farmvest.data.repositories.UserRepository;
import com.visual.status.farmvest.dtos.requests.UserRegistrationRequest;
import com.visual.status.farmvest.dtos.responses.UserRegistrationResponse;
import com.visual.status.farmvest.exceptions.FarmVestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    private UserRegistrationResponse createUser(){
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setFullName("Jack Songu");
        userRegistrationRequest.setEmail("farmvesthusle@gmail.com");
        userRegistrationRequest.setPassword("password");
        userRegistrationRequest.setAddress("Sabo, Yaba");
        userRegistrationRequest.setRoles(Roles.INVESTOR);
        userRegistrationRequest.setPhoneNumber("08133608698");
        return userService.register(userRegistrationRequest);
    }
    @Test
    public void testThatUserCanRegister(){
        UserRegistrationResponse userRegistrationResponse = createUser();
        assertThat(userRegistrationResponse).isNotNull();
        assertThat(userRegistrationResponse.getResponse()).contains("Successfully Registered");
        assertThat(userRegistrationResponse.getEmail()).isEqualTo("farmvesthusle@gmail.com");
        assertThat(userService.getAllUsers().size()).isEqualTo(1L);
    }

    @Test
    public void testThatOneUserCannotRegisterWith_SameEmailTwice(){
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setFullName("Jack Songu");
        userRegistrationRequest.setEmail("farmvesthusle@gmail.com");
        userRegistrationRequest.setPassword("password");
        userRegistrationRequest.setAddress("Sabo, Yaba");
        userRegistrationRequest.setRoles(Roles.INVESTOR);
        userRegistrationRequest.setPassword("password");
        userRegistrationRequest.setPhoneNumber("08133608698");
        assertThrows(FarmVestException.class, () -> userService.register(userRegistrationRequest));
    }
}