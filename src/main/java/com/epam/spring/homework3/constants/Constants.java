package com.epam.spring.homework3.constants;


public class Constants {

    private Constants(){}

    public static final int ROWS = 5;
    public static final String API_URL = "/api/v1";
    public static final String LOGIN_PATH = API_URL+"/login";
    public static final String TOKEN_REFRESH_PATH = API_URL+"/token/refresh";
    public static final String ROLE_CLAIM = "roles";

}
