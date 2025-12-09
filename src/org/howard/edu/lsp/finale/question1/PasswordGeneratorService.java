package org.howard.edu.lsp.finale.question1;

/**
 * Singleton service that provides password generation functionality.
 * This service uses the Strategy pattern to support multiple password generation
 * algorithms that can be selected at runtime. The service ensures only one
 * instance exists and supports future algorithm expansion without modifying client code.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class PasswordGeneratorService {
    
    /*
     * ========================================================================
     * DESIGN PATTERN DOCUMENTATION (PART C)
     * ========================================================================
     * 
     * 1. IDENTIFIED DESIGN PATTERNS:
     *    - Strategy Pattern
     *    - Singleton Pattern
     * 
     * 2. WHY THESE PATTERNS WERE APPROPRIATE:
     * 
     * Strategy Pattern:
     *    The Strategy pattern was chosen because the problem requires:
     *    - Multiple password generation algorithms ("basic", "enhanced", "letters")
     *    - Runtime selection of algorithms (setAlgorithm(String name))
     *    - Swappable behavior (can change algorithms without changing client code)
     *    - Future extensibility (new algorithms can be added by implementing
     *      PasswordAlgorithm interface without modifying existing code)
     *    
     *    The Strategy pattern is implemented through:
     *    - PasswordAlgorithm interface: Defines the contract for all algorithms
     *    - Concrete strategies: BasicPasswordAlgorithm, EnhancedPasswordAlgorithm,
     *      LettersPasswordAlgorithm each implement the interface
     *    - Context: PasswordGeneratorService delegates password generation to the
     *      currently selected algorithm via currentAlgorithm.generate(length)
     *    
     *    This pattern allows the service to select an algorithm at runtime and
     *    change behavior dynamically, meeting the requirement for "swappable behavior"
     *    and "future expansion without modification to client code."
     * 
     * Singleton Pattern:
     *    The Singleton pattern was chosen because the requirement explicitly states:
     *    "Only one instance of the service may exist."
     *    
     *    The Singleton pattern is implemented through:
     *    - Private constructor: Prevents direct instantiation
     *    - Static instance variable: Stores the single instance
     *    - getInstance() method: Provides controlled access to the single instance
     *    - Double-checked locking: Ensures thread-safe singleton creation
     *    
     *    This pattern ensures that all clients use the same PasswordGeneratorService
     *    instance, providing a single shared access point for password generation
     *    throughout the application, which meets the requirement for "a single
     *    shared access point."
     * 
     * ========================================================================
     */
    
    private static PasswordGeneratorService instance;
    private PasswordAlgorithm currentAlgorithm;
    
    /**
     * Private constructor to enforce singleton pattern.
     */
    private PasswordGeneratorService() {
        this.currentAlgorithm = null;
    }
    
    /**
     * Returns the singleton instance of PasswordGeneratorService.
     * 
     * @return the single instance of PasswordGeneratorService
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            synchronized (PasswordGeneratorService.class) {
                if (instance == null) {
                    instance = new PasswordGeneratorService();
                }
            }
        }
        return instance;
    }
    
    /**
     * Sets the password generation algorithm to use.
     * Supported algorithms: "basic", "enhanced", "letters"
     * 
     * @param name the name of the algorithm to use
     * @throws IllegalArgumentException if the algorithm name is not supported
     */
    public void setAlgorithm(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Algorithm name cannot be null");
        }
        
        switch (name.toLowerCase()) {
            case "basic":
                this.currentAlgorithm = new BasicPasswordAlgorithm();
                break;
            case "enhanced":
                this.currentAlgorithm = new EnhancedPasswordAlgorithm();
                break;
            case "letters":
                this.currentAlgorithm = new LettersPasswordAlgorithm();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }
    
    /**
     * Generates a password using the currently selected algorithm.
     * 
     * @param length the desired length of the password
     * @return a generated password string
     * @throws IllegalStateException if no algorithm has been set
     * @throws IllegalArgumentException if length is less than or equal to zero
     */
    public String generatePassword(int length) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm has been set. Call setAlgorithm() first.");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than zero");
        }
        
        return currentAlgorithm.generate(length);
    }
    
    /**
     * Internal interface for password generation algorithms.
     * This interface allows for easy expansion of new algorithms without
     * modifying the service or client code.
     */
    interface PasswordAlgorithm {
        /**
         * Generates a password string of the specified length.
         * 
         * @param length the desired length of the password
         * @return a generated password string
         */
        String generate(int length);
    }
}

