package com.userservice.userservice.validators;

import java.util.regex.Pattern;

public class EmailValidator {
    public boolean validateEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
