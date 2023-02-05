package com.sdacodecoolproject.usersmanager.user.service;

import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.user.dto.UserBuilder;
import com.sdacodecoolproject.usersmanager.user.dto.UserDto;
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
    public User addNewUser(UserDto userDto)
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
                .withIsActive(userDto.isActive())
                .withIsNonLocked(userDto.isNonLocked())
                .withRolePermissions(getRoleEnumName(userDto.getRolePermissions()).name())
                .withAuthorities(getRoleEnumName(userDto.getRolePermissions()).getAuthorities())
                .build();
        userRepository.save(user);
        return user;
    }

    private Role getRoleEnumName(String role){
        return Role.valueOf(role);
    }

    @Override
    public User updateUser(UserDto userDto )
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        User userById = userRepository.findById(userDto.getId()).get();
        User currentUser = checker.checkThatNewUsernameAndEmailNotRepeat(userById.getUsername(), userDto.getUsername(), userDto.getEmail());
        currentUser.setFirstName(userDto.getFirstName());
        currentUser.setLastName(userDto.getLastName());
        currentUser.setRegistrationDate(new Date());
        currentUser.setUsername(userDto.getUsername());
        currentUser.setEmail(userDto.getEmail());
        currentUser.setActive(userDto.isActive());
        currentUser.setNonLocked(userDto.isNonLocked());
        currentUser.setRolePermissions(getRoleEnumName(userDto.getRolePermissions()).name());
        currentUser.setAuthorities(getRoleEnumName(userDto.getRolePermissions()).getAuthorities());
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
