package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password algorithm that generates passwords using java.security.SecureRandom.
 * Output may include uppercase letters (A-Z), lowercase letters (a-z), and digits (0-9).
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
class EnhancedPasswordAlgorithm implements PasswordGeneratorService.PasswordAlgorithm {
    private static final String ALLOWED = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789";
    private final SecureRandom secureRandom;
    
    /**
     * Constructs a new EnhancedPasswordAlgorithm.
     */
    EnhancedPasswordAlgorithm() {
        this.secureRandom = new SecureRandom();
    }
    
    /**
     * Generates a password containing uppercase letters, lowercase letters, and digits.
     * 
     * @param length the desired length of the password
     * @return a generated password string containing A-Z, a-z, and 0-9
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            password.append(ALLOWED.charAt(index));
        }
        return password.toString();
    }
}

