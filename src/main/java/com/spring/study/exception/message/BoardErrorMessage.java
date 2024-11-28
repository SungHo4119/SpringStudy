package com.spring.study.exception.message;

public class BoardErrorMessage {

    public static final String TITLE_LENGTH = "Title must be between 1 and 20 characters";
    public static final String CONTENT_LENGTH = "Content must be between 1 and 500 characters";
    public static final String USER_ID_NOT_NULL = "User ID must not be null";
    public static final String USER_ID_POSITIVE = "User ID must be positive";

    public static final String BOARD_NOT_FOUND = "Board not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String PASSWORD_NOT_MATCH = "Password not match";
}
