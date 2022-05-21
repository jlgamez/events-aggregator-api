package com.jlgamez.eventAgregatorApp.user.userRequests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;

    public UserRegistrationRequest() {}

    public UserRegistrationRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
