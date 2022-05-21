package com.jlgamez.eventAgregatorApp.activity.activityRequests;

import com.jlgamez.eventAgregatorApp.category.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ListIterator;

@Getter
@Setter
public class SearchByCategoriesRequest {

    private List<String> categories;

    // empty constructor
    public SearchByCategoriesRequest() {}


    public SearchByCategoriesRequest(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "SearchByCategoriesRequest{" +
                "categories=" + categories +
                '}';
    }
}
