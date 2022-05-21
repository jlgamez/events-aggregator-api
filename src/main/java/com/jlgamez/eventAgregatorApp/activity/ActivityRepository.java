package com.jlgamez.eventAgregatorApp.activity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {


    // search by title
    @Query("SELECT a FROM Activity a WHERE a.title = ?1")
    Optional<Activity> findActivityByTitle(String title);

}
