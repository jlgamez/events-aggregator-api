package com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserActivitySuccess {

    private String message;
    private Long userId;
    private Long activityId;
    private final boolean userActivitySaved = true;

    public NewUserActivitySuccess() {
    }

    public NewUserActivitySuccess(Long userId, Long activityId) {
        final String messageTemplate = "Activity with id %s saved for the user with id %s";
        this.userId = userId;
        this.activityId = activityId;
        this.message = String.format(messageTemplate, activityId, userId);
    }
}
