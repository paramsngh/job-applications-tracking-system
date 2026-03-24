package com.company.company.utils;

import java.util.UUID;

public class CompanyNameGenerator {

    public static String generateRandomName() {

        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        return "testcompany" + uniqueId;

    }

}