package com.sdacodecoolproject.usersmanager.user.login.service;

import com.sdacodecoolproject.usersmanager.application.constant.UserServicesConstant;
import com.sdacodecoolproject.usersmanager.user.login.CurrentUser;
import com.sdacodecoolproject.usersmanager.user.repository.UserRepository;

import com.sdacodecoolproject.usersmanager.user.model.User;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class LoginService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            logger.error(UserServicesConstant.NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(UserServicesConstant.NO_USER_FOUND_BY_USERNAME + username);
        } else {
            user.setLastLoginDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            logger.info(UserServicesConstant.FOUND_USER_BY_USERNAME + username);
            return new CurrentUser(user);
        }
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


}
