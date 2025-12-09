package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password algorithm that generates passwords using java.util.Random.
 * Output contains digits only (0-9).
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
class BasicPasswordAlgorithm implements PasswordGeneratorService.PasswordAlgorithm {
    private static final String DIGITS = "0123456789";
    private final Random random;
    
    /**
     * Constructs a new BasicPasswordAlgorithm.
     */
    BasicPasswordAlgorithm() {
        this.random = new Random();
    }
    
    /**
     * Generates a password containing only digits.
     * 
     * @param length the desired length of the password
     * @return a generated password string containing only digits (0-9)
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}

