package com.sdacodecoolproject.usersmanager.user.registration.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.model.User;

public interface RegistrationService {

    User register(String firstName, String lastName, String email, String username, String password) throws UserNotFoundException, UsernameExistException, EmailExistException;

}
