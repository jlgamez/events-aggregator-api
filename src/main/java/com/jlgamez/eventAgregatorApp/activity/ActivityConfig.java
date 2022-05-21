package com.jlgamez.eventAgregatorApp.activity;

import com.jlgamez.eventAgregatorApp.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ActivityConfig {
    private final CategoryRepository categoryRepository;

    // Activity demo objects
    private final Activity demoAct1;
    private final Activity demoAct2;
    private final Activity demoAct3;
    private final  Activity demoAct4;
    private final Activity demoAct5;
    private final Activity demoAct6;
    private final Activity demoAct7;
    private final Activity demoAct8;
    private final Activity demoAct9;
    private final Activity demoAct10;
    private final Activity demoAct11;
    private final Activity demoAct12;
    private final Activity demoAct13;
    private final Activity demoAct14;
    private final Activity demoAct15;
    private final Activity demoAct16;



    private List<Activity> demoActivities = new ArrayList<Activity>();

    @Autowired
    public ActivityConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

        demoAct1 = new Activity(
        "Kayaking in Barceloneta",
        "The ultimate rowing experience in your city!",
                LocalDate.of(2022, Month.JULY, 6),
        "Barceloneta",
        "https://www.tripadvisor.es/AttractionProductReview-g187497-d16823214-Kayaking_and_Snorkeling_on_la_Costa_Brava_Small_group-Barcelona_Catalonia.html",
                categoryRepository.getById(4L));

        demoAct2 = new Activity(
        "The Picasso Museum",
        "Enjoy one of the most popular Arts museums in barcelona",
                LocalDate.of(2022, Month.DECEMBER, 7),
        "Carrer de Montcada, 15-23",
        "http://www.museupicasso.bcn.cat/es/",
                categoryRepository.getById(2L));

        demoAct3 = new Activity(
        "Concert by Stu Larsen",
        "A concert by an Australian Nomad musician who has nowhere to go back to",
                LocalDate.of(2022, Month.MAY, 15),
        "Palau de la Música Catalana",
        "https://www.viagogo.es/Entradas-Conciertos/Folk/Stu-Larsen-Entradas",
                categoryRepository.getById(3L));

        demoAct4 = new Activity(
        "Barcelona Treasure Hunt",
        "Explore the city with an immersive group experience",
                LocalDate.of(2022, Month.SEPTEMBER, 23),
        "Passeig de Gracia",
        "https://www.mystery.city/barcelona",
                categoryRepository.getById(5L));

        demoAct5 = new Activity(
        "Play basketball in Barcelona",
        "Make new friends and compete by playing basketball",
                LocalDate.of(2022, Month.AUGUST, 2),
        "CEM Mundet",
        "https://www.meetup.com/es-ES/Barcelona-Basketball",
                categoryRepository.getById(1L));

        demoAct6 = new Activity(
        "Hiking near Barcelona",
        "Enjoy the fresh air of Barcelona Mountains",
                LocalDate.of(2022, Month.NOVEMBER, 10),
        "Colserola",
        "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(4L));

        demoAct7 = new Activity(
                "Concert in the center of barcelona",
                "The center of barcelona will be hosting a concert of free access for everyone",
                LocalDate.of(2022, Month.JULY, 20),
                "Barcelona Center",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(3L));

        demoAct8 = new Activity(
                "Fitness session with BarceloGym",
                "Join a gum session to get fit and get to know more people",
                LocalDate.of(2022, Month.JANUARY, 12),
                "Carrer gimnasio, 254",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(1L));

        demoAct9 = new Activity(
                "Art Exhibition in the Museum of Catalan Arts",
                "In this exhibition you will see the best work of top Barcelona painters",
                LocalDate.of(2022, Month.NOVEMBER, 10),
                "Museum of Catalan Arts ",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(2L));

        demoAct10 = new Activity(
                "City tour in the North of Barcelona",
                "Enjoy the fresh air of Barcelona Mountains",
                LocalDate.of(2022, Month.MAY, 27),
                "Gracia Neighborhood",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(5L));

        demoAct11 = new Activity(
                "Camping outside Barcelona",
                "Join barcelona campers in a trip in the depths of the provice",
                LocalDate.of(2022, Month.APRIL, 8),
                "Barcelona province",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(4L));

        demoAct12 = new Activity(
                "Trekking in Montserrat",
                "Meet one of the most beautiful catalan sceneries",
                LocalDate.of(2022, Month.AUGUST, 15),
                "Montserrat",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(4L));

        demoAct13 = new Activity(
                "Gastronomic tour in bars",
                "Try the best tapas in the city in a 3-hour tour",
                LocalDate.of(2022, Month.SEPTEMBER, 23),
                "Barcelona center",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(5L));

        demoAct14 = new Activity(
                "Group yoga in Poble Nou",
                "If you like stretching and training your balance, come to this yoga session in Poble Nou",
                LocalDate.of(2022, Month.DECEMBER, 3),
                "Pble Nou",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(1L));

        demoAct15 = new Activity(
                "Running club in the city",
                "For those expats that are into running, this is footing club to run in Barcelona in good company",
                LocalDate.of(2022, Month.JUNE, 6),
                "Passeig de Gracia",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(1L));

        demoAct16 = new Activity(
                "Solidary concert by Margarita de Atiragram",
                "One of the most famous fictitious musicians in Milan will land in barcelona soon!",
                LocalDate.of(2022, Month.JUNE, 6),
                "Carrer de Patricia Aicirtap, 230",
                "https://www.viator.com/tours/Barcelona/Hiking-and-Trekking-Near-Barcelona/d562-86458P2",
                categoryRepository.getById(3L));
    }


    @Bean
    CommandLineRunner activityCommandLineRunner (ActivityRepository activityRepository) {

        return args -> {

            activityRepository.save(demoAct1);
            activityRepository.save(demoAct2);
            activityRepository.save(demoAct3);
            activityRepository.save(demoAct4);
            activityRepository.save(demoAct5);
            activityRepository.save(demoAct6);
            activityRepository.save(demoAct7);
            activityRepository.save(demoAct8);
            activityRepository.save(demoAct9);
            activityRepository.save(demoAct10);
            activityRepository.save(demoAct11);
            activityRepository.save(demoAct12);
            activityRepository.save(demoAct13);
            activityRepository.save(demoAct14);
            activityRepository.save(demoAct15);
            activityRepository.save(demoAct16);
        };
    }

}
