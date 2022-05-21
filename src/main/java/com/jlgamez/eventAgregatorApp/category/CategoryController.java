package com.jlgamez.eventAgregatorApp.category;

import com.jlgamez.eventAgregatorApp.category.categoryRequests.NewCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/categories")
@CrossOrigin //this annotation allows the API to be consumed by javascript apps
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping(path = "add-new")
    @ResponseBody
    public ResponseEntity saveNewCategory(@RequestBody NewCategoryRequest categoryRequest) {
        return categoryService.saveNewCategory(categoryRequest);
    }
}
