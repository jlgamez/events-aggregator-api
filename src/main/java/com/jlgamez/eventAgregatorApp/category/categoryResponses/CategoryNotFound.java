package com.jlgamez.eventAgregatorApp.category.categoryResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNotFound {

    private final String message;

    public CategoryNotFound(Long id) {
        String messageTemplate = "Category with id %s not found";
        this.message = String.format(messageTemplate, id);
    }
}
