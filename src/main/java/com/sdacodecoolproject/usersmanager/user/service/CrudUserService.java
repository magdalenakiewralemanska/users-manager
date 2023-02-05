package com.sdacodecoolproject.usersmanager.user.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.dto.UserDto;
import com.sdacodecoolproject.usersmanager.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudUserService {

    User addNewUser(UserDto userDto) throws UserNotFoundException, EmailExistException, UsernameExistException;
    User updateUser(UserDto userDto) throws UserNotFoundException, EmailExistException, UsernameExistException;
    List<User> findAllUsers();

    void deleteUser(Long id);
}
