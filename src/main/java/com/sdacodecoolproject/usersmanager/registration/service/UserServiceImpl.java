package com.sdacodecoolproject.usersmanager.registration.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.application.model.Role;
import com.sdacodecoolproject.usersmanager.application.model.User;
import com.sdacodecoolproject.usersmanager.application.repository.UserRepository;
import com.sdacodecoolproject.usersmanager.application.constant.UserImplConstant;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String firstName, String lastName, String email, String username)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        checkThatNewUsernameAndEmailNotRepeat(StringUtils.EMPTY, username, email);
        User user = new User();
        user.setUserIdNumber(generateUserId());
        String password = generatePassword();
        String encodedPassword = encodePassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setRegistrationDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setRolePermissions(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        user.setImageUrl(getImageUrl());
        userRepository.save(user);
        logger.info("New user password: " + password);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private String getImageUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/image/profile/temp")
                .toUriString();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(15);
    }

    private User checkThatNewUsernameAndEmailNotRepeat(String currentUsername, String newUsername, String newEmail)
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
        User userByNewEmail = findUserByEmail(newEmail);
        if(userByNewEmail != null) {
            throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkThatUsernameNotRepeatForRegistration(String newUsername) throws UsernameExistException {
        User userByNewUsername = findUserByUsername(newUsername);
        if(userByNewUsername != null) {
            throw new UsernameExistException(UserImplConstant.USERNAME_ALREADY_EXISTS);
        }
    }

    private void checkThatEmailNotRepeatForUpdate(String newEmail, User currentUser) throws EmailExistException {
        User userByNewEmail = findUserByEmail(newEmail);
        if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
            throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkThatUsernameNotRepeatForUpdate(String newUsername, User currentUser) throws UsernameExistException {
        User userByNewUsername = findUserByUsername(newUsername);
        if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
            throw new UsernameExistException(UserImplConstant.USERNAME_ALREADY_EXISTS);
        }
    }

    private User checkThatUserExists(String currentUsername) throws UserNotFoundException {
        User currentUser = findUserByUsername(currentUsername);
        if(currentUser == null) {
            throw new UserNotFoundException(UserImplConstant.NO_USER_FOUND_BY_USERNAME + currentUsername);
        }
        return currentUser;
    }
}
