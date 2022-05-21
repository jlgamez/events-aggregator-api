package com.jlgamez.eventAgregatorApp.user.userRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    private String userEmail;
    private String password;

    public UserLoginRequest() {}

    public UserLoginRequest(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}
