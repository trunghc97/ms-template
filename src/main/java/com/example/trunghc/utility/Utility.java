package com.example.trunghc.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    public String generatePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public boolean checkPassword(String rawPassword, String hashPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, hashPassword);
    }
}
