package com.jobtracker.user_service.utils;

import java.util.UUID;

public class TestDataGenerator {

    public static String generateRandomEmail() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        return "testuser_" + uniqueId + "@email.com";
    }
}
