package com.jlgamez.eventAgregatorApp.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CategoryConfig {
    private final Category sports = new Category("sports");
    private final Category culture = new Category("culture");
    private final Category music = new Category("music");
    private final Category outdoors = new Category("outdoors");
    private final Category city = new Category("city");

    @Bean
    CommandLineRunner categoryCommandLineRunner(CategoryRepository categoryRepository) {
        return args -> {
            categoryRepository.save(sports);
            categoryRepository.save(culture);
            categoryRepository.save(music);
            categoryRepository.save(outdoors);
            categoryRepository.save(city);

        };
    }
}
