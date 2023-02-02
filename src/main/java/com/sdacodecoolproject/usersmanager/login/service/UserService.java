package com.sdacodecoolproject.usersmanager.login.service;

import com.sdacodecoolproject.usersmanager.login.model.User;

import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String email, String username) throws Exception;
    List<User> getUsers();

    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
