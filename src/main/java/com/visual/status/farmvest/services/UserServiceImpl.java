package com.visual.status.farmvest.services;

import com.visual.status.farmvest.data.models.User;
import com.visual.status.farmvest.data.repositories.UserRepository;
import com.visual.status.farmvest.dtos.requests.UserRegistrationRequest;
import com.visual.status.farmvest.dtos.responses.UserRegistrationResponse;
import com.visual.status.farmvest.exceptions.FarmVestException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) {
        validateUserEmail(userRegistrationRequest.getEmail().trim().toLowerCase());
        passwordEncoder.encode(userRegistrationRequest.getPassword());
        User user = modelMapper.map(userRegistrationRequest, User.class);
        userRepository.save(user);
        UserRegistrationResponse userRegistrationResponse = modelMapper.map(user, UserRegistrationResponse.class);
        userRegistrationResponse.setResponse("Successfully Registered");
        userRegistrationResponse.setUserId(userRegistrationResponse.getUserId());
        userRegistrationResponse.setEmail(userRegistrationResponse.getEmail());
        return userRegistrationResponse;
    }
    private void validateUserEmail(String email){
        boolean existsByEmail = userRepository.existsByEmail(email);
        if(existsByEmail) throw new FarmVestException("Email Already Exists");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
