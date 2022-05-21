package com.jlgamez.eventAgregatorApp.activity;

import com.jlgamez.eventAgregatorApp.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Activity {
    @Id
    @SequenceGenerator(
            name = "activity_sequence",
            sequenceName = "activity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_sequence"
    )
    private Long id;
    @Column
    private String title;
    @Column(length = 500)
    private String description;
    @Column
    private LocalDate date;
    @Column
    private String location;
    @Column(length = 400)
    private String bookingLink;
    @ManyToOne
    @JoinColumn
    private Category category;

    // empty constructor
    public Activity() {}

    // constructor with all data
    public Activity(Long id, String title, String description, LocalDate date, String location, String bookingLink, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.bookingLink = bookingLink;
        this.category = category;
    }

    // constructor without id
    public Activity(String title, String description, LocalDate date, String location, String bookingLink, Category category) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.bookingLink = bookingLink;
        this.category = category;
    }


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", bookingLink='" + bookingLink + '\'' +
                ", category=" + category +
                '}';
    }
}
