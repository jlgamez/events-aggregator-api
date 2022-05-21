package com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivityNotFound {

    private String message;

    public UserActivityNotFound() {}

    public UserActivityNotFound(Long id) {
        String messageTemplate = "User activity with id %s not found";
        this.message = String.format(messageTemplate, id);
    }
}
