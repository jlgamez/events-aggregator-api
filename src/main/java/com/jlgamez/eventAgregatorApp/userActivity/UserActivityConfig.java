package com.jlgamez.eventAgregatorApp.userActivity;

import com.jlgamez.eventAgregatorApp.activity.ActivityRepository;
import com.jlgamez.eventAgregatorApp.activity.ActivityService;
import com.jlgamez.eventAgregatorApp.user.UserRepository;
import com.jlgamez.eventAgregatorApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserActivityConfig {
    private UserRepository userRepository;
    private ActivityRepository activityRepository;

    // UserActivity demo objects
    private final UserActivity demoUsrAct1;
    private final UserActivity demoUsrAct2;
    private final UserActivity DemoUsrAct3;
    private final UserActivity DemoUsrAct4;

    @Autowired
    public UserActivityConfig( UserRepository userRepository, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;

        this.demoUsrAct1 = new UserActivity(
                userRepository.getById(1L),
                activityRepository.getById(1L));
        this.demoUsrAct2 = new UserActivity(
                userRepository.getById(1L),
                activityRepository.getById(2L)
        );
        this.DemoUsrAct3 = new UserActivity(
                userRepository.getById(1L),
                activityRepository.getById(3L)
        );
        this.DemoUsrAct4 = new UserActivity(
                userRepository.getById(2L),
                activityRepository.getById(4L)
        );

    }


    @Bean
    CommandLineRunner userActivityCommandLineRunner(UserActivityRepository userActivityRepository){
        return args -> {
            userActivityRepository.save(demoUsrAct1);
            userActivityRepository.save(demoUsrAct2);
            userActivityRepository.save(DemoUsrAct3);
            userActivityRepository.save(DemoUsrAct4);

        };
    }

}
