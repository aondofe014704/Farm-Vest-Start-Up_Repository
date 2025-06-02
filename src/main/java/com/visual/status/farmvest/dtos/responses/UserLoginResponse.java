package com.visual.status.farmvest.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginResponse {
    private String email;
    private String message;
    private boolean isLoggedIn;
}
