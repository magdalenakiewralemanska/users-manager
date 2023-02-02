package com.sdacodecoolproject.usersmanager.login.service;

import com.sdacodecoolproject.usersmanager.login.constant.UserImplConstant;
import com.sdacodecoolproject.usersmanager.login.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.login.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.login.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.login.model.CurrentUser;
import com.sdacodecoolproject.usersmanager.login.model.Role;
import com.sdacodecoolproject.usersmanager.login.repository.UserRepository;

import com.sdacodecoolproject.usersmanager.login.model.User;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            logger.error(UserImplConstant.NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(UserImplConstant.NO_USER_FOUND_BY_USERNAME + username);
        } else {
            user.setLastLoginDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            logger.info(UserImplConstant.FOUND_USER_BY_USERNAME + username);
            return new CurrentUser(user);
        }
    }

    @Override
    public User register(String firstName, String lastName, String email, String username)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateUsernameAndEmail(StringUtils.EMPTY, username, email);
        User user = new User();
        user.setUserIdNumber(generateUserId());
        String password = generatePassword();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setRegistrationDate(new Date());
        user.setPassword(password);
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

    private String generatePassword() {
        return passwordEncoder.encode(RandomStringUtils.randomAlphanumeric(15));
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(15);
    }

    private void validateUsernameAndEmail(String currentUsername, String newUsername, String email) throws
            UserNotFoundException, UsernameExistException, EmailExistException {
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = checkThatUserExists(currentUsername);
            checkThatUsernameNotRepeat(newUsername, currentUser);
            checkThatEmailNotRepeat(email, currentUser);
        } else {
            User userByUsername = findUserByUsername(newUsername);
            if(userByUsername != null){
                throw new UsernameExistException(UserImplConstant.USERNAME_ALREADY_EXISTS);
            }
            User userByEmail = findUserByEmail(email);
            if(userByEmail != null){
                throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS);
            }
        }
    }

    private void checkThatEmailNotRepeat(String email, User currentUser) throws EmailExistException {
        User userByEmail = findUserByEmail(email);
        if(userByEmail != null && !currentUser.getId().equals(userByEmail.getId())){
            throw new EmailExistException(UserImplConstant.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkThatUsernameNotRepeat(String newUsername, User currentUser) throws UsernameExistException {
        User userByUsername = findUserByUsername(newUsername);
        if(userByUsername != null && !currentUser.getId().equals(userByUsername.getId())){
            throw new UsernameExistException(UserImplConstant.USERNAME_ALREADY_EXISTS);
        }
    }

    private User checkThatUserExists(String currentUsername) throws UserNotFoundException{
        User currentUser = findUserByUsername(currentUsername);
        if(currentUser == null){
            throw new UserNotFoundException("Username " + currentUsername + " not found");
        }
        return currentUser;
    }
}
