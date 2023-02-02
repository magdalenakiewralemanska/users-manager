package com.sdacodecoolproject.usersmanager.application.exception;

public class UsernameExistException extends Exception{
    public UsernameExistException(String message) {
        super(message);
    }
}
