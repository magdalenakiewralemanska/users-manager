package com.sdacodecoolproject.usersmanager.application.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sdacodecoolproject.usersmanager.application.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String INCORRECT_CREDENTIALS = "Password or username is incorrect. Please try again log in.";
    private static final String ACCOUNT_IS_LOCKED = "Your account has been locked. You have to contact with administrator of this service.";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. You have to contact with administrator of this service.";
    private static final String WITHOUT_PERMISSION = "You don't have permission to do that";
    private static final String INTERNAL_SERVER_ERROR = "Something went wrong during request processing";

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
        HttpResponse httpResponse = new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase(), message);
        return new ResponseEntity<>(httpResponse, httpStatus);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> incorrectCredentialException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> accountIsLockedException(){
        return createHttpResponse(HttpStatus.UNAUTHORIZED, ACCOUNT_IS_LOCKED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException(){
        return createHttpResponse(HttpStatus.FORBIDDEN, WITHOUT_PERMISSION);
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException e){
        return createHttpResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> emailNotExistedException(EmailExistException e){
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> usernameNotExistedException(UsernameExistException e){
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException e){
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException e){
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> internalServerException(Exception e){
        logger.error(e.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
    }

}
