package com.jlgamez.eventAgregatorApp.user.userLogin;

import com.jlgamez.eventAgregatorApp.user.UserService;
import com.jlgamez.eventAgregatorApp.user.userRequests.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/login")
@CrossOrigin
public class UserLoginController {

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity userLogin(@RequestBody UserLoginRequest loginRequest) {
        return userService.userLogin(loginRequest);
    }
}
