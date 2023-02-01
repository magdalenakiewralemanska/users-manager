package com.sdacodecoolproject.usersmanager.login.service.impl;

import com.sdacodecoolproject.usersmanager.login.model.CurrentUser;
import com.sdacodecoolproject.usersmanager.login.repository.UserRepository;
import com.sdacodecoolproject.usersmanager.login.service.UserService;

import com.sdacodecoolproject.usersmanager.login.model.User;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
