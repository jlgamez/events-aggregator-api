package com.jlgamez.eventAgregatorApp.activity.activityResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewActivityRejected {
    private final String message;

    public NewActivityRejected(String message) {

        this.message = message;
    }
}
