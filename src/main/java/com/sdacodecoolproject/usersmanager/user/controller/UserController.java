package com.sdacodecoolproject.usersmanager.user.controller;

import com.sdacodecoolproject.usersmanager.application.constant.UserServicesConstant;
import com.sdacodecoolproject.usersmanager.application.exception.EmailExistException;
import com.sdacodecoolproject.usersmanager.application.exception.ExceptionHandle;
import com.sdacodecoolproject.usersmanager.application.exception.UserNotFoundException;
import com.sdacodecoolproject.usersmanager.application.exception.UsernameExistException;
import com.sdacodecoolproject.usersmanager.application.model.HttpResponse;
import com.sdacodecoolproject.usersmanager.user.dto.UserDto;
import com.sdacodecoolproject.usersmanager.user.model.User;
import com.sdacodecoolproject.usersmanager.user.repository.UserRepository;
import com.sdacodecoolproject.usersmanager.user.service.CrudUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends ExceptionHandle {

    private final CrudUserService crudUserService;
    private final UserRepository userRepository;

    public UserController(CrudUserService crudUserService, UserRepository userRepository) {
        this.crudUserService = crudUserService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<User> saveNewUser(@RequestBody UserDto userDto) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User newUser = crudUserService.addNewUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User updatedUser = crudUserService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        User user = userRepository.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = crudUserService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE')")
    public ResponseEntity<HttpResponse> deleteUserById(@PathVariable("id") Long id){
        crudUserService.deleteUser(id);
        return new ResponseEntity<>(new HttpResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK,
                HttpStatus.OK.getReasonPhrase(),
                UserServicesConstant.USER_DELETED),
                HttpStatus.OK);
    }

}
