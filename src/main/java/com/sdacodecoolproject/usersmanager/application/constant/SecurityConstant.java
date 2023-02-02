package com.sdacodecoolproject.usersmanager.application.constant;

public class SecurityConstant {

    public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Manager";
    public static final String AUTHORITIES = "Authorities";
    public static final long EXPIRATION_TIME = 259_200_000;
    public static final String NOT_VERIFIED_TOKEN = "Token not verified";
    public static final String TOKEN_FRONT = "Bearer ";
    public static final String HTTP_METHODS = "OPTIONS";
    public static final String FORBIDDEN = "You have to log in first";
    public static final String ACCESS_DENIED = "Your permissions are insufficient";
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "/user/resetpassword/**", "/user/image/**"};
    public static final String TOKEN_HEADER = "Jwt-Token";


}
