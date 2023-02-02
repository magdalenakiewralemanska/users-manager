package com.sdacodecoolproject.usersmanager.login.service;

import com.sdacodecoolproject.usersmanager.login.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.login.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.login.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.login.model.User;

import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String email, String username) throws UserNotFoundException, UsernameExistException, EmailExistException;
    List<User> getUsers();

    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
