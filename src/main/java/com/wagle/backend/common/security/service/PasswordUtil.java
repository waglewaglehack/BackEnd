package com.wagle.backend.common.security.service;

import java.util.UUID;

public class PasswordUtil {
    public static String generateRandomPassword() {
        return UUID.randomUUID().toString();
    }
}
