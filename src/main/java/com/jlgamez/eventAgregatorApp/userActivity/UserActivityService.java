package com.jlgamez.eventAgregatorApp.userActivity;

import com.jlgamez.eventAgregatorApp.activity.Activity;
import com.jlgamez.eventAgregatorApp.activity.ActivityRepository;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByCategoriesRequest;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.SearchByKeywordsRequest;
import com.jlgamez.eventAgregatorApp.activity.dataProcessing.ActivityDataProcessor;
import com.jlgamez.eventAgregatorApp.user.User;
import com.jlgamez.eventAgregatorApp.user.UserRepository;
import com.jlgamez.eventAgregatorApp.user.userResponses.UserNotFound;
import com.jlgamez.eventAgregatorApp.userActivity.userActivityRequests.NewUserActivityRequest;
import com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses.NewUserActivityRejected;
import com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses.NewUserActivitySuccess;
import com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses.UserActivityDeleted;
import com.jlgamez.eventAgregatorApp.userActivity.userActivityResponses.UserActivityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserActivityService {
    private final UserActivityRepository userActivityRepository;

    // Need to access User and Activity repositories
    // to get objects from those entities
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;


    @Autowired
    public UserActivityService(UserActivityRepository userActivityRepository, UserRepository userRepository, ActivityRepository activityRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    public List<UserActivity> getAllUsersActivities() {
        return userActivityRepository.findAll();
    }

    public ResponseEntity getUserActivitiesByUserId(Long userId) {
        ResponseEntity response;
        // check first if user exists
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            // return a response with:
            //  * List of activities for that user (might be empty)
            //  * status OK
            User targetUser = userRepository.getById(userId);
            response = new ResponseEntity(
                    userActivityRepository.findUserActivitiesByUser(targetUser),
                    HttpStatus.OK
            );
        } else {
            response = new ResponseEntity(
                    new UserNotFound(userId),
                    HttpStatus.NOT_FOUND
            );
        }

        return response;
    }

    public ResponseEntity saveNewUserActivity(NewUserActivityRequest userActivityRequest) {
        ResponseEntity response;
        Long userId = userActivityRequest.getUserId();
        Long activityId = userActivityRequest.getActivityId();

        // reject the request if the user or the activity dont exist
        Optional<User> optUsr = userRepository.findById(userId);
        Optional<Activity> optAct = activityRepository.findById(activityId);

        if (optUsr.isEmpty() ||optAct.isEmpty()) {
            response = new ResponseEntity(
                    new NewUserActivityRejected(
                            "The user or activity requested were not found",
                            userId,
                            activityId),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        // If the user and activity exist, fetch them
        User u = userRepository.getById(userId);
        Activity a = activityRepository.getById(activityId);

        /*
        check if there is an existing user activity with the provided
        user and activity
         */
        Optional<UserActivity> optionalUserActivity = userActivityRepository.findByUserAndActivity(u, a);

        if (optionalUserActivity.isPresent()) {
            //  reject the new user activity when it exists already
            response = new ResponseEntity(
                    new NewUserActivityRejected(
                            "You have already saved this activity",
                            userId,
                            activityId),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        } else {
            // Register the UserActivity object
            UserActivity userActivity = new UserActivity(u, a);
            userActivityRepository.save(userActivity);
            response = new ResponseEntity(
                    new NewUserActivitySuccess(userId, activityId),
                    HttpStatus.OK
            );
        }
        return response;
    }

    public ResponseEntity deleteUserActivity(Long userActivityId) {
        ResponseEntity response;
        // check if the target userActivity exists
        Optional<UserActivity> targetUserAct = userActivityRepository.findById(userActivityId);

        if (targetUserAct.isPresent()) {
            // delete the user activity
            UserActivity usrActToDelete = userActivityRepository.getById(userActivityId);
            userActivityRepository.delete(usrActToDelete);

            response = new ResponseEntity(
                    new UserActivityDeleted(usrActToDelete),
                    HttpStatus.OK
            );
            return response;
        }

        // when user activity does not exist inform of it
        response = new ResponseEntity(
                new UserActivityNotFound(userActivityId),
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    public List<UserActivity> searchByKeywords(SearchByKeywordsRequest searchRequest, Long userId) {
        // get all user activities
        List<UserActivity> allUserActivities = userActivityRepository.findAll();
        // keep only those matching the userId
        List<UserActivity> allCurrentUserActivities = new ArrayList<>();
        for (UserActivity ua : allUserActivities) {
            if (ua.getUser().getId().equals(userId)) {allCurrentUserActivities.add(ua);}
        }
        // return only those containing at least one keyword in title
        List<String> keywords = searchRequest.getKeywords();
        ActivityDataProcessor processor = new ActivityDataProcessor(true);
        return processor.getMatchingUserActivities(allCurrentUserActivities, keywords);
    }

    public List<UserActivity> searchByCategory(SearchByCategoriesRequest searchRequest, Long userId) {
        // get all user activities
        List<UserActivity> allUserActivities = userActivityRepository.findAll();
        // keep only those matching the userId
        List<UserActivity> allCurrentUserActivities = new ArrayList<>();
        for (UserActivity ua : allUserActivities) {
            if (ua.getUser().getId().equals(userId)) {allCurrentUserActivities.add(ua);}
        }
        // return only those containing at least one keyword in category
        List<String> categories = searchRequest.getCategories();
        ActivityDataProcessor processor = new ActivityDataProcessor(false);
        return processor.getMatchingUserActivities(allCurrentUserActivities, categories);
    }

}
