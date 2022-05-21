package com.jlgamez.eventAgregatorApp.activity.activityRequests;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class NewActivityRequest {
    private String title;
    private String description;
    private LocalDate  date;
    private String location;
    private String bookingLink;
    private Long categoryId;

    public NewActivityRequest() {
    }
    public NewActivityRequest(String title, String description, LocalDate date, String location, String bookingLink, Long categoryId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.bookingLink = bookingLink;
        this.categoryId = categoryId;
    }

}
