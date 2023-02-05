package com.sdacodecoolproject.usersmanager.user.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudUserService {

    User addNewUser(String firstName, String lastName, String username, String email, String password, String role, boolean isNonLocked, boolean isActive) throws UserNotFoundException, EmailExistException, UsernameExistException;
    User updateUser(String currentUsername, String firstName, String lastName, String username, String email,  boolean isActive, boolean isNonLocked, String role) throws UserNotFoundException, EmailExistException, UsernameExistException;
    List<User> findAllUsers();

    void deleteUser(Long id);
}
