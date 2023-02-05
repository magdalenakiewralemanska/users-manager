package com.sdacodecoolproject.usersmanager.user.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.helpers.IdGenerator;
import com.sdacodecoolproject.usersmanager.user.model.Role;
import com.sdacodecoolproject.usersmanager.user.model.User;
import com.sdacodecoolproject.usersmanager.user.repository.UserRepository;
import com.sdacodecoolproject.usersmanager.user.helpers.UsernameAndEmailRepeatingChecker;

import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CrudUserServiceImpl implements CrudUserService {

    private final UserRepository userRepository;
    private final UsernameAndEmailRepeatingChecker checker;
    private final IdGenerator generator;
    private final BCryptPasswordEncoder passwordEncoder;

    public CrudUserServiceImpl(UserRepository userRepository, UsernameAndEmailRepeatingChecker checker,
                               IdGenerator generator, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.checker = checker;
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addNewUser(String firstName, String lastName, String username, String email, String password, String role, boolean isNonLocked, boolean isActive)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        checker.checkThatNewUsernameAndEmailNotRepeat(StringUtils.EMPTY, username, email);
        User user = new User();
        user.setUserIdNumber(generator.generateUserId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRegistrationDate(new Date());
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(isActive);
        user.setNotLocked(isNonLocked);
        user.setRolePermissions(getRoleEnumName(role).name());
        user.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepository.save(user);
        return user;
    }

    private Role getRoleEnumName(String role){
        return Role.valueOf(role);
    }

    @Override
    public User updateUser(String currentUsername, String firstName, String lastName,  String username, String email, boolean isActive, boolean isNonLocked, String role )
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        User currentUser = checker.checkThatNewUsernameAndEmailNotRepeat(currentUsername, username, email);
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setRegistrationDate(new Date());
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        currentUser.setActive(isActive);
        currentUser.setNotLocked(isNonLocked);
        currentUser.setRolePermissions(getRoleEnumName(role).name());
        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepository.save(currentUser);
        return currentUser;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
