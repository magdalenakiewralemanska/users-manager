package com.sdacodecoolproject.usersmanager.user.registration.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.helpers.IdGenerator;
import com.sdacodecoolproject.usersmanager.user.helpers.UsernameAndEmailRepeatingChecker;
import com.sdacodecoolproject.usersmanager.user.model.Role;
import com.sdacodecoolproject.usersmanager.user.model.User;
import com.sdacodecoolproject.usersmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final UsernameAndEmailRepeatingChecker checker;
    private final IdGenerator generator;

    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, UsernameAndEmailRepeatingChecker checker,
                                   IdGenerator generator, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.checker = checker;
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String firstName, String lastName, String email, String username, String password)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        checker.checkThatNewUsernameAndEmailNotRepeat(StringUtils.EMPTY, username, email);
        User user = new User();
        user.setUserIdNumber(generator.generateUserId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setRegistrationDate(new Date());
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setNotLocked(true);
        user.setRolePermissions(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        userRepository.save(user);
        return user;
    }



}
