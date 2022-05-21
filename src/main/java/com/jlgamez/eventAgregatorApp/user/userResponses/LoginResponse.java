package com.jlgamez.eventAgregatorApp.user.userResponses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Getter
@Setter
public class LoginResponse {
    private String message;
    private boolean isLogin;
    private Long userId = null;
    private String userName = null;


    public LoginResponse() {}

    public LoginResponse(String userEmail, @Nullable Long userId, @Nullable String userName,  boolean passwordMatch) {
        this.message = String.format(
                determineLoginResultMessage(userEmail, userId, passwordMatch, userName),
                userEmail);
    }

    private String determineLoginResultMessage(String userEmail, Long userId, boolean passwordMatch, String userName) {
        String messageTemplate;

        // first check an email was provided
        if (!userEmail.contains("@")) {
            messageTemplate = "Provide a valid email";
            isLogin = false;
            return messageTemplate;
        }

        if (userId == null) {
            messageTemplate = "The user email %s was not found";
            isLogin = false;
            return messageTemplate;
        }

        if (!passwordMatch) {
            messageTemplate = "The password for the user %s is incorrect";
            isLogin = false;
            return messageTemplate;
        }

        // If the previous checks pass inform of successful login
        messageTemplate = "Login successful";
        isLogin = true;
        this.userId = userId;
        this.userName = userName;
        return messageTemplate;
    }
}
