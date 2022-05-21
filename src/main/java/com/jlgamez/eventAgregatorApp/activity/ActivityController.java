package com.jlgamez.eventAgregatorApp.activity;


import com.jlgamez.eventAgregatorApp.activity.activityRequests.NewActivityRequest;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByCategoriesRequest;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByKeywordsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/activities")
@CrossOrigin //this annotation allows the API to be consumed by javascript apps
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping(path = "search-by-keywords")
    public List<Activity> searchActivitiesByKeywords(@RequestBody SearchByKeywordsRequest searchRequest) {
        return activityService.searchByKeywords(searchRequest);
    }

    @PostMapping(path = "search-by-categories")
    public List<Activity> searchActivitiesByCategories(@RequestBody SearchByCategoriesRequest searchRequest) {
        return activityService.searchByCategories(searchRequest);
    }

    @PostMapping(path = "add-new")
    @ResponseBody
    public ResponseEntity saveNewActivity(@RequestBody NewActivityRequest activityRequest) {
        return activityService.saveNewActivity(activityRequest);
    }
}
