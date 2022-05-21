package com.jlgamez.eventAgregatorApp.activity;

import com.jlgamez.eventAgregatorApp.activity.activityRequests.NewActivityRequest;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByCategoriesRequest;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByKeywordsRequest;
import com.jlgamez.eventAgregatorApp.activity.activityResponses.NewActivityRejected;
import com.jlgamez.eventAgregatorApp.activity.activityResponses.NewActivitySuccess;
import com.jlgamez.eventAgregatorApp.activity.dataProcessing.ActivityDataProcessor;
import com.jlgamez.eventAgregatorApp.category.Category;
import com.jlgamez.eventAgregatorApp.category.CategoryRepository;
import com.jlgamez.eventAgregatorApp.category.categoryResponses.CategoryNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, CategoryRepository categoryRepository) {
        this.activityRepository = activityRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity getActivityById(Long activityId) {
        // getBy method always returns an object otherwhise throws an exception
        // Use the method when you are sure the object exists
        return activityRepository.getById(activityId);
    }

    public ResponseEntity saveNewActivity(NewActivityRequest newActivityRequest) {
        ResponseEntity response;

        // check if the data provided is correct
        ActivityDataProcessor processor = new ActivityDataProcessor(false);
        if (!processor.isInfoProvided(newActivityRequest.getTitle())) {
            response = new ResponseEntity(
                    new NewActivityRejected("Activity not created. Provide a title"),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        if (!processor.isInfoProvided(newActivityRequest.getLocation())){
            response = new ResponseEntity(
                    new NewActivityRejected("Activity not created. Provide a location"),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        if (!processor.isInfoProvided(newActivityRequest.getBookingLink())) {
            response = new ResponseEntity(
                    new NewActivityRejected("Activity not created. Provide a booking link"),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        if (!processor.isCategoryIdProvided(newActivityRequest.getCategoryId())) {
            response = new ResponseEntity(
                    new NewActivityRejected("Activity not created. Choose a category"),
                    HttpStatus.BAD_REQUEST

            );
            return response;
        }

        // check if there is an existing activity with the provided title
        Optional<Activity> optionalActivity = activityRepository.findActivityByTitle(newActivityRequest.getTitle());
        if (optionalActivity.isPresent()) {
            // reject the new activity when title is duplicated
            response = new ResponseEntity(
                    new NewActivityRejected("New activity rejected. Title is duplicated"),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        // check if the chosen category for the activity exists
        Optional<Category> optionalCategory = categoryRepository.findById(newActivityRequest.getCategoryId());
        if (optionalCategory.isEmpty()) {
            // reject the new activity when its category does not exist
            response = new ResponseEntity(
                    new CategoryNotFound(newActivityRequest.getCategoryId()),
                    HttpStatus.NOT_FOUND
            );
            return response;
        }

        // If the request passes the previous checks, save the new activity

        Activity newActivity = new Activity(
                newActivityRequest.getTitle(),
                newActivityRequest.getDescription(),
                newActivityRequest.getDate(),
                newActivityRequest.getLocation(),
                newActivityRequest.getBookingLink(),
                categoryRepository.getById(newActivityRequest.getCategoryId())
        );
        activityRepository.save(newActivity);

        response = new ResponseEntity(
                new NewActivitySuccess(
                        newActivityRequest.getTitle(),
                        newActivityRequest.getDescription(),
                        newActivityRequest.getDate(),
                        newActivityRequest.getLocation(),
                        newActivityRequest.getBookingLink(),
                        newActivityRequest.getCategoryId()
                ),
                HttpStatus.OK
        );
        return response;
    }

    public List<Activity> searchByKeywords(SearchByKeywordsRequest searchRequest) {
        // get all activities in the database
        List<Activity> allActivities = activityRepository.findAll();
        // return only those containing at least one keyword in title
        List<String> keywords = searchRequest.getKeywords();
        ActivityDataProcessor processor = new ActivityDataProcessor(true);
        return processor.getMatchingActivities(allActivities, keywords);
    }


    public List<Activity> searchByCategories(SearchByCategoriesRequest searchRequest) {
        // get all activities in the database
        List<Activity> allActivities = activityRepository.findAll();
        // return only those matching at least partially on category
        List<String> categories = searchRequest.getCategories();
        ActivityDataProcessor processor = new ActivityDataProcessor(false);
        return processor.getMatchingActivities(allActivities, categories);
    }
}



