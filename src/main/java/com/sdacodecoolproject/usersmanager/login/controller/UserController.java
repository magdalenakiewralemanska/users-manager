package com.sdacodecoolproject.usersmanager.login.controller;

import com.sdacodecoolproject.usersmanager.login.constant.SecurityConstant;
import com.sdacodecoolproject.usersmanager.login.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.login.exception.ExceptionHandle;
import com.sdacodecoolproject.usersmanager.login.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.login.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.login.model.CurrentUser;
import com.sdacodecoolproject.usersmanager.login.model.User;
import com.sdacodecoolproject.usersmanager.login.security.token.TokenProvider;
import com.sdacodecoolproject.usersmanager.login.service.UserServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/user")
public class UserController extends ExceptionHandle {

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;

    private final TokenProvider tokenProvider;

    public UserController(AuthenticationManager authenticationManager, UserServiceImpl userService, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        CurrentUser currentUser= new CurrentUser(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(currentUser);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(CurrentUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstant.TOKEN_HEADER, tokenProvider.generateToken(user));
        return headers;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User loginUser = userService.register(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername());
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }
}
