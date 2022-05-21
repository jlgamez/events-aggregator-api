package com.jlgamez.eventAgregatorApp.userActivity;

import com.jlgamez.eventAgregatorApp.activity.Activity;
import com.jlgamez.eventAgregatorApp.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class UserActivity {
    @Id
    @SequenceGenerator(
            name = "user_activity_sequence",
            sequenceName = "user_activity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_activity_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Activity activity;

    // empty constructor
    public UserActivity() {
    }

    // Constructor with all data
    public UserActivity(Long id, User user, Activity activity) {
        this.id = id;
        this.user = user;
        this.activity = activity;
    }

    // constructor without id
    public UserActivity(User user, Activity activity) {
        this.user = user;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "id=" + id +
                ", user=" + user +
                ", activity=" + activity +
                '}';
    }
}
