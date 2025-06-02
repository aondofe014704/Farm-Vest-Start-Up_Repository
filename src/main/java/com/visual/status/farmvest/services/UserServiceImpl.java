package com.visual.status.farmvest.services;

import com.visual.status.farmvest.data.models.User;
import com.visual.status.farmvest.data.repositories.UserRepository;
import com.visual.status.farmvest.dtos.requests.UserLoginRequest;
import com.visual.status.farmvest.dtos.requests.UserRegistrationRequest;
import com.visual.status.farmvest.dtos.responses.UserLoginResponse;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) {
        validateUserEmail(userRegistrationRequest.getEmail().trim().toLowerCase());
        String encodedPassword = passwordEncoder.encode(userRegistrationRequest.getPassword());
        User user = modelMapper.map(userRegistrationRequest, User.class);
        user.setPassword(encodedPassword);
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

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = findByEmail(userLoginRequest.getEmail());
        validatePassword(user, userLoginRequest.getPassword());
        user.setLoggedIn(true);
        userRepository.save(user);
        UserLoginResponse userLoginResponse = modelMapper.map(user, UserLoginResponse.class);
        userLoginResponse.setMessage("Successfully Logged In");
        userLoginResponse.setEmail(userLoginResponse.getEmail());
        return userLoginResponse;
    }
    private void validatePassword(User user, String password){
    if (!passwordEncoder.matches(password, user.getPassword()))
        throw new FarmVestException("Invalid Details");
    }
    private User findByEmail(String email){
    return userRepository.findByEmail(email)
            .orElseThrow(()-> new FarmVestException("User Not Found"));
    }
}
