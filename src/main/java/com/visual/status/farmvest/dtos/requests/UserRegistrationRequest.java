package com.visual.status.farmvest.dtos.requests;

import com.visual.status.farmvest.data.models.Roles;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationRequest {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Roles roles;
}
