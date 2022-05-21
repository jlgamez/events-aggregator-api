package com.jlgamez.eventAgregatorApp.category;

import com.jlgamez.eventAgregatorApp.category.categoryRequests.NewCategoryRequest;
import com.jlgamez.eventAgregatorApp.category.categoryResponses.NewCategoryRejected;
import com.jlgamez.eventAgregatorApp.category.categoryResponses.NewCategorySuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ResponseEntity saveNewCategory(NewCategoryRequest categoryRequest) {
        String newCategoryName = categoryRequest.getName().toLowerCase();
        ResponseEntity response;

        // check if category already exists in the database
        Optional<Category> optionalCategory = categoryRepository.findCategoryByName(newCategoryName);

        if (optionalCategory.isPresent()) {
            // reject the new category when name is duplicated
            response = new ResponseEntity(
                    new NewCategoryRejected(newCategoryName),
                    HttpStatus.BAD_REQUEST
            );
        } else {
            // save the new category
            response = new ResponseEntity(
                    new NewCategorySuccess(newCategoryName),
                    HttpStatus.OK
            );

            Category newCategory = new Category(newCategoryName);
            categoryRepository.save(newCategory);
        }
        return response;
    }
}
