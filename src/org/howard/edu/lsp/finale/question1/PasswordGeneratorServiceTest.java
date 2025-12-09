package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for PasswordGeneratorService.
 * This test class verifies singleton behavior, exception handling,
 * and correct functionality of all password generation algorithms.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    /**
     * Sets up the test fixture before each test method.
     * Retrieves the singleton instance of PasswordGeneratorService.
     */
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        // TODO: verify that 'service' is not null
        assertNotNull(service, "Service instance should not be null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // TODO: Verify that both 'service' (created in @BeforeEach) 
        // and 'second' refer to the EXACT same object in memory. This 
        // test must confirm true singleton behavior — not just that the 
        // two objects are equal, but they are the *same 
        // instance* returned by getInstance().
        assertSame(service, second, "Both instances should refer to the same object in memory");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // TODO: verify correct exception behavior for each algorithm
        // First, ensure no algorithm is set (by using a new instance or resetting)
        // Since we can't directly reset, we'll test that calling generatePassword
        // without setAlgorithm throws IllegalStateException
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> s.generatePassword(10),
            "generatePassword should throw IllegalStateException when no algorithm is set"
        );
        assertNotNull(exception.getMessage(), "Exception should have a message");
        assertTrue(exception.getMessage().contains("algorithm"), 
            "Exception message should mention algorithm");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        // TODO: verify required behavior
        assertNotNull(p, "Generated password should not be null");
        assertEquals(10, p.length(), "Password should have correct length");
        assertTrue(p.matches("[0-9]+"), 
            "Basic algorithm should generate digits only (0-9)");
        
        // Test with different length
        String p2 = service.generatePassword(5);
        assertEquals(5, p2.length(), "Password should have correct length");
        assertTrue(p2.matches("[0-9]+"), 
            "Basic algorithm should generate digits only");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        // TODO: verify required behavior
        assertNotNull(p, "Generated password should not be null");
        assertEquals(12, p.length(), "Password should have correct length");
        assertTrue(p.matches("[A-Za-z0-9]+"), 
            "Enhanced algorithm should generate A-Z, a-z, and 0-9 characters only");
        
        // Verify pattern is correct for alphanumeric characters
        // Note: Due to randomness, we can't guarantee every single password has all types,
        // but the pattern should allow A-Z, a-z, and 0-9
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        // TODO: verify required behavior
        assertNotNull(p, "Generated password should not be null");
        assertEquals(8, p.length(), "Password should have correct length");
        assertTrue(p.matches("[A-Za-z]+"), 
            "Letters algorithm should generate letters only (A-Z, a-z)");
        
        // Test with different length
        String p2 = service.generatePassword(15);
        assertEquals(15, p2.length(), "Password should have correct length");
        assertTrue(p2.matches("[A-Za-z]+"), 
            "Letters algorithm should generate letters only");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // TODO: verify correct behavior characteristics of each algorithm
        // Verify p1 (basic) contains only digits
        assertNotNull(p1, "Basic password should not be null");
        assertEquals(10, p1.length(), "Basic password should have correct length");
        assertTrue(p1.matches("[0-9]+"), 
            "After setting 'basic', password should contain digits only");
        
        // Verify p2 (letters) contains only letters
        assertNotNull(p2, "Letters password should not be null");
        assertEquals(10, p2.length(), "Letters password should have correct length");
        assertTrue(p2.matches("[A-Za-z]+"), 
            "After setting 'letters', password should contain letters only");
        
        // Verify p3 (enhanced) contains alphanumeric characters
        assertNotNull(p3, "Enhanced password should not be null");
        assertEquals(10, p3.length(), "Enhanced password should have correct length");
        assertTrue(p3.matches("[A-Za-z0-9]+"), 
            "After setting 'enhanced', password should contain A-Z, a-z, and 0-9");
        
        // Verify algorithms produce different results (behavior changed)
        // Basic should not match letters pattern
        assertFalse(p1.matches("[A-Za-z]+"), 
            "Basic password should not match letters pattern");
        // Letters should not match digits pattern
        assertFalse(p2.matches("[0-9]+"), 
            "Letters password should not match digits pattern");
    }
}
