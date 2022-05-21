package com.jlgamez.eventAgregatorApp.user;

import com.jlgamez.eventAgregatorApp.user.userRequests.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/users")
@CrossOrigin //this annotation allows the API to be consumed by javascript apps
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/register")
    @ResponseBody /* tells spring to serielise in JSON and
                  return in http response bodythe object returned from this method
                   */
    public ResponseEntity registerNewUser(@RequestBody UserRegistrationRequest registrationRequest) {
        return userService.registerNewUser(registrationRequest);
    }

}
