package com.jlgamez.eventAgregatorApp.activity.activityResponses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewActivitySuccess {
    private final String message = "New activity succesfully accepted.";
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private String bookingLink;
    private Long categoryId;

    public NewActivitySuccess(String title, String description, LocalDate date, String location, String bookingLink, Long categoryId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.bookingLink = bookingLink;
        this.categoryId = categoryId;
    }
}
