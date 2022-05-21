package com.jlgamez.eventAgregatorApp.activity.dataProcessing;


import com.jlgamez.eventAgregatorApp.activity.Activity;
import com.jlgamez.eventAgregatorApp.activity.activityRequests.NewActivityRequest;
import com.jlgamez.eventAgregatorApp.userActivity.UserActivity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;

@Getter
@Setter
public class ActivityDataProcessor {

    private boolean searchingByKeywords;

    public ActivityDataProcessor(boolean searchingByKeywords) {
        this.searchingByKeywords = searchingByKeywords;
    }

    public List<UserActivity> getMatchingUserActivities(List<UserActivity> allUserActivities, List<String> searchStrings) {
        // put the user activities in a map and extract the activity object from the user activity
        Map<UserActivity, Activity> userActivityActivityMap = new HashMap<UserActivity, Activity>();
        List<Activity> activities = new ArrayList<Activity>();
        for (UserActivity ua : allUserActivities) {
            Activity act = ua.getActivity();
            userActivityActivityMap.put(ua, act);
            activities.add(act);
        }
        // filter out non-matching activities
        List<Activity> matchingActivities = getMatchingActivities(activities, searchStrings);

        // return only the user activities that contain the matching activities
        List<UserActivity> matchingUserActivities = new ArrayList<>();
        for (Map.Entry<UserActivity, Activity> entry : userActivityActivityMap.entrySet()) {
            Activity evaluatedActivity = entry.getValue();
            if (matchingActivities.stream().anyMatch(matchedActivity -> evaluatedActivity.equals(matchedActivity))){
                matchingUserActivities.add(entry.getKey());
            }
        }

        return matchingUserActivities;
    }

    public List<Activity> getMatchingActivities(List<Activity> allActivities, List<String> searchStrings) {
        List<Activity> matchingActivities = new ArrayList<Activity>();

        // remove all empty search strings
        searchStrings.removeAll(Arrays.asList("", " ", null));

        // put the activities in a map
        Map<Activity, String> activitiesMap = mapActivities(allActivities);

        // make all searchStrings lowercase
        ListIterator<String> iterator = searchStrings.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }

        // put in the final list the activities which title or category
        // contain at least one search string
        for (Map.Entry<Activity, String> entry : activitiesMap.entrySet()) {
            String evaluatedString = entry.getValue();

            if (searchStrings.stream().anyMatch(keyword -> evaluatedString.toLowerCase().contains(keyword))) {
                matchingActivities.add(entry.getKey());
            }
        }
        return matchingActivities;
    }

    private Map<Activity, String> mapActivities(List<Activity> allActivities) {
        Map<Activity, String> mappedActivities = new HashMap<Activity, String>();

        if (searchingByKeywords) {
            // compare search strings against titles
            for (Activity a : allActivities) {
                mappedActivities.put(a, a.getTitle());
            }
        } else {
            // compare search strings against categories
            for (Activity a : allActivities) {
                mappedActivities.put(a, a.getCategory().getName());
            }
        }
        return  mappedActivities;
    }

    public Boolean isInfoProvided(String info) {
        Boolean infoCorrect = true ;
        // check for empty title
        if (info.trim().isEmpty()) {
            infoCorrect = false;
        }
        return infoCorrect;
    }

    public Boolean isCategoryIdProvided(Long i) {
        Boolean categoryIdProvided = true;
        if (i == null) {
            categoryIdProvided = false;
        }
        return categoryIdProvided;
    }


}
