package com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserActivityRejected {
    private String message;
    private Long userId;
    private Long activityId;
    private final boolean userActivitySaved = false;

    public NewUserActivityRejected() {
    }

    public NewUserActivityRejected(String message, Long userId, Long activityId) {
        this.message = message;
        this.userId = userId;
        this.activityId = activityId;
    }
}
