package com.visual.status.farmvest.services;

import com.visual.status.farmvest.data.models.User;
import com.visual.status.farmvest.dtos.requests.UserRegistrationRequest;
import com.visual.status.farmvest.dtos.responses.UserRegistrationResponse;

import java.util.List;

public interface UserService {

    UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest);
    List <User> getAllUsers();

}
