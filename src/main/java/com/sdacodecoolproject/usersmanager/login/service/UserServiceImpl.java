package com.sdacodecoolproject.usersmanager.login.service;

import com.sdacodecoolproject.usersmanager.login.model.CurrentUser;
import com.sdacodecoolproject.usersmanager.login.model.Role;
import com.sdacodecoolproject.usersmanager.login.repository.UserRepository;

import com.sdacodecoolproject.usersmanager.login.model.User;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            logger.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            user.setLastLoginDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            return new CurrentUser(user);
        }
    }

    @Override
    public User register(String firstName, String lastName, String email, String username) throws Exception {
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
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
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

    private void validateUsernameAndEmail(String currentUsername, String newUsername, String email) throws Exception {
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = checkThatUserExists(currentUsername);
            checkThatUsernameNotRepeat(newUsername, currentUser);
            checkThatEmailNotRepeat(email, currentUser);
        }
    }

    private void checkThatEmailNotRepeat(String email, User currentUser) throws Exception {
        User userByEmail = findUserByEmail(email);
        if(userByEmail != null){
            throw  new Exception("Email already exists");
        }
    }

    private void checkThatUsernameNotRepeat(String newUsername, User currentUser) throws Exception {
        User newUser = findUserByUsername(newUsername);
        if(newUser != null){
            throw new Exception("Username already exists");
        }
    }

    private User checkThatUserExists(String currentUsername) {
        User currentUser = findUserByUsername(currentUsername);
        if(currentUser == null){
            throw new UsernameNotFoundException("Username " + currentUsername + " not found");
        }
        return currentUser;
    }
}
