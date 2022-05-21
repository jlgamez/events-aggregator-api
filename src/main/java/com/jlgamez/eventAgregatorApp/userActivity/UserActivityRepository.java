package com.jlgamez.eventAgregatorApp.userActivity;

import com.jlgamez.eventAgregatorApp.activity.Activity;
import com.jlgamez.eventAgregatorApp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    @Query("SELECT ua FROM UserActivity ua WHERE ua.user = ?1")
    List<UserActivity> findUserActivitiesByUser(User user);

    @Query("SELECT ua FROM UserActivity ua WHERE ua.user = ?1 AND ua.activity = ?2")
    Optional<UserActivity> findByUserAndActivity(User user, Activity activity);

    List<UserActivity> findByUser(User user);

}
