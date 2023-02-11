package com.single.commerce_project.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    public String encryptPassword(String password) {
        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return encPassword;
    }
}
