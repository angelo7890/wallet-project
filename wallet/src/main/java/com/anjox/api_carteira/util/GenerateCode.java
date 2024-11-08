package com.anjox.api_carteira.util;

import java.security.SecureRandom;

public class GenerateCode {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateCode(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(number));
        }
        return code.toString();
    }
}
