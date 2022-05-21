package com.jlgamez.eventAgregatorApp.userActivity.userActivityRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserActivityRequest {

    private Long userId;
    private Long activityId;

    // empty constructor
    public NewUserActivityRequest() {}

    public NewUserActivityRequest(Long userId, Long activityId) {
        this.userId = userId;
        this.activityId = activityId;
    }
}
