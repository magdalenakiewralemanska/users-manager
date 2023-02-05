package com.sdacodecoolproject.usersmanager.user.registration.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.dto.UserBuilder;
import com.sdacodecoolproject.usersmanager.user.dto.UserDto;
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
    public User register(UserDto userDto)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        checker.checkThatNewUsernameAndEmailNotRepeat(StringUtils.EMPTY, userDto.getUsername(), userDto.getEmail());
        User user = new UserBuilder()
                .withUserIdNumber(generator.generateUserId())
                .withFirstName(userDto.getFirstName())
                .withLastName(userDto.getLastName())
                .withEmail(userDto.getEmail())
                .withPassword(passwordEncoder.encode(userDto.getPassword()))
                .withUsername(userDto.getUsername())
                .withRegistrationDate(new Date())
                .withIsActive(true)
                .withIsNonLocked(true)
                .withRolePermissions(Role.ROLE_USER.name())
                .withAuthorities(Role.ROLE_USER.getAuthorities())
                .build();
        userRepository.save(user);
        return user;
    }

}
