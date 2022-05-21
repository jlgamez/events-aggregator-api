package com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses;

import com.jlgamez.eventAgregatorApp.userActivity.UserActivity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivityDeleted {

    private final String message = "User Activity successfully deleted";
    private UserActivity deletedUserActivity;

    public UserActivityDeleted() {}
    public UserActivityDeleted(UserActivity deletedUserAct) {
        this.deletedUserActivity = deletedUserAct;
    }
}
