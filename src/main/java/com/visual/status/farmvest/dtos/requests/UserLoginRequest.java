package com.visual.status.farmvest.dtos.requests;

import com.visual.status.farmvest.data.models.Roles;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {
    private String email;
    private String password;
    private Roles roles;
}
