package com.sdacodecoolproject.usersmanager.registration.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.application.model.User;

import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String email, String username) throws UserNotFoundException, UsernameExistException, EmailExistException;
    List<User> getUsers();

    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
