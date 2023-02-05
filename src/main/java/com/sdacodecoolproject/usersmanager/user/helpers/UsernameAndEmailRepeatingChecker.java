package com.sdacodecoolproject.usersmanager.user.helpers;

import com.sdacodecoolproject.usersmanager.application.constant.UserServicesConstant;
import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.model.User;
import com.sdacodecoolproject.usersmanager.user.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class UsernameAndEmailRepeatingChecker {

    private final UserRepository userRepository;

    public UsernameAndEmailRepeatingChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkThatNewUsernameAndEmailNotRepeat(String currentUsername, String newUsername, String newEmail)
            throws UserNotFoundException, UsernameExistException, EmailExistException {
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = checkThatUserExists(currentUsername);
            checkThatUsernameNotRepeatForUpdate(newUsername, currentUser);
            checkThatEmailNotRepeatForUpdate(newEmail, currentUser);
            return currentUser;
        } else {
            checkThatUsernameNotRepeatForRegistration(newUsername);
            checkThatEmailNotRepeatForRegistration(newEmail);
            return null;
        }
    }

    private void checkThatEmailNotRepeatForRegistration(String newEmail) throws EmailExistException {
        User userByNewEmail = userRepository.findUserByEmail(newEmail);
        if(userByNewEmail != null) {
            throw new EmailExistException(UserServicesConstant.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkThatUsernameNotRepeatForRegistration(String newUsername) throws UsernameExistException {
        User userByNewUsername = userRepository.findUserByUsername(newUsername);
        if(userByNewUsername != null) {
            throw new UsernameExistException(UserServicesConstant.USERNAME_ALREADY_EXISTS);
        }
    }

    private void checkThatEmailNotRepeatForUpdate(String newEmail, User currentUser) throws EmailExistException {
        User userByNewEmail = userRepository.findUserByEmail(newEmail);
        if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
            throw new EmailExistException(UserServicesConstant.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkThatUsernameNotRepeatForUpdate(String newUsername, User currentUser) throws UsernameExistException {
        User userByNewUsername = userRepository.findUserByUsername(newUsername);
        if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
            throw new UsernameExistException(UserServicesConstant.USERNAME_ALREADY_EXISTS);
        }
    }

    private User checkThatUserExists(String currentUsername) throws UserNotFoundException {
        User currentUser = userRepository.findUserByUsername(currentUsername);
        if(currentUser == null) {
            throw new UserNotFoundException(UserServicesConstant.NO_USER_FOUND_BY_USERNAME + currentUsername);
        }
        return currentUser;
    }
}
