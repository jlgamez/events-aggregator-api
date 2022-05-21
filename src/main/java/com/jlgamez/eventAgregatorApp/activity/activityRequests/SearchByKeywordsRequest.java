package com.jlgamez.eventAgregatorApp.activity.activityRequests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchByKeywordsRequest {

    private List<String> keywords;


    // empty constructor
    public SearchByKeywordsRequest() {
    }

    public SearchByKeywordsRequest(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "SearchByKeywordsRequest{" +
                "keywords=" + keywords +
                '}';
    }
}
