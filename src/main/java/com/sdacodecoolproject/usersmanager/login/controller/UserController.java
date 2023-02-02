package com.sdacodecoolproject.usersmanager.login.controller;

import com.sdacodecoolproject.usersmanager.login.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.login.exception.ExceptionHandle;
import com.sdacodecoolproject.usersmanager.login.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.login.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.login.model.User;
import com.sdacodecoolproject.usersmanager.login.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends ExceptionHandle {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException {
       User loginUser = userService.register(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername());
       return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }
}
