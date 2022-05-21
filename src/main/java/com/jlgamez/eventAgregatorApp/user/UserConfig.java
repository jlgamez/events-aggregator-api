package com.jlgamez.eventAgregatorApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final User userDemo1 = new User("Alex", "alex@email.com", "passwd");
    private final User UserDemo2 = new User("Pedro", "pedro@email.com", "1234");



    @Bean
    CommandLineRunner commandLineRunner (UserRepository userRepository ) {
        return args -> {
            // save demo users
            userRepository.save(userDemo1); // this user will have id 1
            userRepository.save(UserDemo2); // this user will have id 2
        };
    }
}
