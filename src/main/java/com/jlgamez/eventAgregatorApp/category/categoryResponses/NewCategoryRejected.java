package com.jlgamez.eventAgregatorApp.category.categoryResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCategoryRejected {
    private final String message = "New category rejected due to duplicated category name";
    private String name;

    public NewCategoryRejected(String name) {
        this.name = name;
    }
}
