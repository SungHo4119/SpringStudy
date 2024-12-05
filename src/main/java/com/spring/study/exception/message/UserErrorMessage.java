package com.spring.study.exception.message;

public class UserErrorMessage {

    public static final String USER_NAME_LENGTH = "User name must be between 4 and 10 characters";
    public static final String USER_NAME_NOT_BLANK = "userName is NotBlank";

    public static final String PASSWORD_LENGTH = "Password must be between 8 and 15 characters";
    public static final String PASSWORD_PATTERN = "Password must contain at least one letter, one number and one special character";
    public static final String PASSWORD_NOT_MATCH = "Password and password check do not match";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";

}
