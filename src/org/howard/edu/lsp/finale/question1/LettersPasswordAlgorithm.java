package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Letters password algorithm that generates passwords containing letters only.
 * Output contains uppercase letters (A-Z) and lowercase letters (a-z).
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
class LettersPasswordAlgorithm implements PasswordGeneratorService.PasswordAlgorithm {
    private static final String LETTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz";
    private final Random random;
    
    /**
     * Constructs a new LettersPasswordAlgorithm.
     */
    LettersPasswordAlgorithm() {
        this.random = new Random();
    }
    
    /**
     * Generates a password containing only letters (uppercase and lowercase).
     * 
     * @param length the desired length of the password
     * @return a generated password string containing only letters (A-Z, a-z)
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}

