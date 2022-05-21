package com.jlgamez.eventAgregatorApp.category.categoryRequests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class NewCategoryRequest {
    private String name;

    public NewCategoryRequest() {
    }
    public NewCategoryRequest(String name) {
        this.name = name;
    }
}
