package com.jlgamez.eventAgregatorApp.category.categoryResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCategorySuccess {
    private final String message = "New category successfully accepted.";
    private String name;

    public NewCategorySuccess(String name) {
        this.name = name;
    }
}
