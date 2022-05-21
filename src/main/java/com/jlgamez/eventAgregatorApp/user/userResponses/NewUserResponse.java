package com.jlgamez.eventAgregatorApp.user.userResponses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class NewUserResponse {
    private String message;
    private Long userId;
    private String name;
    private String email;


    public NewUserResponse(boolean emailTaken, String name, String email, String password, @Nullable Long userId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.message = determineRegistrationResultMessage(emailTaken, name, email, password);
    }

    private String determineRegistrationResultMessage(boolean emailTaken, String name, String email, String password) {
        String messageTemplate;

        // check the name contains letters
        String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher nameMatcher = pattern.matcher(name);
        if (!nameMatcher.matches()) {
            messageTemplate = "Please provide a name";
            return messageTemplate;
        }

        // chekck the email is valid
        if (!email.contains("@")) {
            messageTemplate = "Please provide a valid email";
            return messageTemplate;
        }

        // check password was provided
        // make sure it does not contain ONLY white spaces
        if (password.trim().isEmpty()) {
            messageTemplate = "Please create a password";
            return messageTemplate;
        }

        // if email is taken return a message informing of it
        if (emailTaken) {
            messageTemplate = String.format("The email %s is already in use", email);
            return messageTemplate;
        }

        // if the previous checks pass return a confirmation message
        messageTemplate = String.format("User created with email %s Welcome %s", email, name);
        return  messageTemplate;
    }
}
