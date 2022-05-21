package com.jlgamez.eventAgregatorApp.userActivity;

import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByCategoriesRequest;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByKeywordsRequest;
import com.jlgamez.eventAgregatorApp.userActivity.userActivityRequests.NewUserActivityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user-activities")
@CrossOrigin
public class UserActivityController {

    private final UserActivityService userActivityService;

    @Autowired
    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @GetMapping(path = "all")
    @ResponseBody
    public List<UserActivity> getAllUsersActivities() {
        return userActivityService.getAllUsersActivities();
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity getUserActivitiesByUser(@RequestParam Long userId) {
        return userActivityService.getUserActivitiesByUserId(userId);
    }

    @PostMapping(path = "add-new")
    @ResponseBody
    public ResponseEntity saveNewUserActivity(@RequestBody NewUserActivityRequest userActivityRequest) {
        return userActivityService.saveNewUserActivity(userActivityRequest);
    }

    @DeleteMapping(path = "delete")
    @ResponseBody
    public ResponseEntity deleteUserActivity(@RequestParam Long userActivityId) {
        return userActivityService.deleteUserActivity(userActivityId);

    }

    @PostMapping(path = "search-by-keywords")
    public List<UserActivity> searchUserActivitiesByKeywords(@RequestBody SearchByKeywordsRequest searchRequest, @RequestParam Long userId) {
        return userActivityService.searchByKeywords(searchRequest,userId);
    }

    @PostMapping(path = "search-by-categories")
    public List<UserActivity> searchUserActivitiesByCategories(@RequestBody SearchByCategoriesRequest searchRequest, @RequestParam Long userId) {
        return userActivityService.searchByCategory(searchRequest, userId);
    }

}
