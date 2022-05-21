package com.jlgamez.eventAgregatorApp.user.userResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFound {
    private String message;

    public UserNotFound() {
    }

    public UserNotFound(Long id) {
        String messageTemplate = "User with id %s not found";
        this.message = String.format(messageTemplate, id);
    }
}
