package com.jlgamez.eventAgregatorApp.user;

import com.jlgamez.eventAgregatorApp.user.userRequests.UserLoginRequest;
import com.jlgamez.eventAgregatorApp.user.userRequests.UserRegistrationRequest;
import com.jlgamez.eventAgregatorApp.user.userResponses.LoginResponse;
import com.jlgamez.eventAgregatorApp.user.userResponses.NewUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    // private final String USER_NOTFOUND_MESSAGE = "user with email %s was not found";

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        // getBy method always returns an object otherwise throws an exception
        // Use the method when you are sure the object exists
        return userRepository.getById(userId);
    }

    public ResponseEntity registerNewUser(UserRegistrationRequest registrationRequest) {
        ResponseEntity response;

        // check the name contains letters
        String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher nameMatcher = pattern.matcher(registrationRequest.getName());
        if (!nameMatcher.matches()) {
            response = new ResponseEntity(
                    new NewUserResponse(
                            false,
                            registrationRequest.getName(),
                            registrationRequest.getEmail(),
                            registrationRequest.getPassword(),
                            null
                    ),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        // check a valid email was provided
        if (!registrationRequest.getEmail().contains("@")){
            response = new ResponseEntity(
                    new NewUserResponse(
                            false,
                            registrationRequest.getName(),
                            registrationRequest.getEmail(),
                            registrationRequest.getPassword(),
                            null
                    ),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        // check the password is not empty
        if (registrationRequest.getPassword().trim().isEmpty()) {
            response = new ResponseEntity(
                    new NewUserResponse(
                            false,
                            registrationRequest.getName(),
                            registrationRequest.getEmail(),
                            registrationRequest.getPassword(),
                            null
                    ),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        // check if there is an existing user with the provided email
        Optional<User> optionalUser = userRepository.findUserByEmail(registrationRequest.getEmail());
        if (optionalUser.isPresent()) {
            // reject the new user when email is taken
            response = new ResponseEntity(
                    new NewUserResponse(
                            true,
                            registrationRequest.getName(),
                            registrationRequest.getEmail(),
                            registrationRequest.getPassword(),
                            null
                    ),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        // if previous checks pass, save the user
        User newUser = new User(
                registrationRequest.getName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword()
        );
        userRepository.save(newUser);

        response = new ResponseEntity(
                new NewUserResponse(
                        false,
                        registrationRequest.getName(),
                        registrationRequest.getEmail(),
                        registrationRequest.getPassword(),
                        userRepository.getUserByEmail(registrationRequest.getEmail()).getId()
                ),
                HttpStatus.OK
        );
        return response;
    }

    public ResponseEntity userLogin(UserLoginRequest loginRequest) {
        ResponseEntity response;

        // Check if the user exists
        Optional<User> optionalUser = userRepository.findUserByEmail(loginRequest.getUserEmail());
        if (optionalUser.isEmpty()) {
            // fail Login when user does not exist
            LoginResponse failedLoginResponse = new LoginResponse(
                    loginRequest.getUserEmail(),
                    null,
                    null,
                    false
            );
            response = new ResponseEntity(failedLoginResponse, HttpStatus.UNAUTHORIZED);
            return response;
        }

        // Check if password matches
        User existingUser = userRepository.getById(optionalUser.get().getId());
        String existingUserPassword = existingUser.getPassword();
        if (!existingUserPassword.equals(loginRequest.getPassword())) {
            // fail login when passwords do not match
            LoginResponse failedLoginResponse = new LoginResponse(
                    loginRequest.getUserEmail(),
                    existingUser.getId(),
                    null,
                    false
            );
            response = new ResponseEntity(failedLoginResponse, HttpStatus.UNAUTHORIZED);
            return response;
        }

        // If the previous checks pass log the uer in
        LoginResponse loginSuccessResponse = new LoginResponse(
                loginRequest.getUserEmail(),
                existingUser.getId(),
                existingUser.getName(),
                true
        );
        response = new ResponseEntity(loginSuccessResponse, HttpStatus.OK);
        return response;
    }

}
