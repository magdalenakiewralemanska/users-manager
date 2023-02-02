package com.sdacodecoolproject.usersmanager.login.controller;

import com.sdacodecoolproject.usersmanager.application.constant.SecurityConstant;
import com.sdacodecoolproject.usersmanager.application.exception.ExceptionHandle;
import com.sdacodecoolproject.usersmanager.application.model.CurrentUser;
import com.sdacodecoolproject.usersmanager.application.model.User;
import com.sdacodecoolproject.usersmanager.login.security.token.TokenProvider;
import com.sdacodecoolproject.usersmanager.login.service.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/user")
public class UserController extends ExceptionHandle {

    private final AuthenticationManager authenticationManager;
    private final LoginService userService;

    private final TokenProvider tokenProvider;

    public UserController(AuthenticationManager authenticationManager, LoginService userService, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        CurrentUser currentUser= new CurrentUser(loginUser);
        HttpHeaders headers = getHeader(currentUser);
        return new ResponseEntity<>(loginUser, headers, OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getHeader(CurrentUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstant.TOKEN_HEADER, tokenProvider.generateToken(user));
        return headers;
    }
}
