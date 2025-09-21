package org.howard.edu.lsp.assignment3;

/**
 * Validates product data integrity and CSV row format.
 * This class encapsulates all validation logic, following the Single Responsibility Principle.
 * It provides static utility methods for validating different aspects of product data.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class ProductValidator {
    
    /**
     * Validates a CSV row to ensure it has the correct number of fields.
     * 
     * @param parts the array of CSV field parts
     * @return true if the row has exactly 4 fields, false otherwise
     */
    public static boolean validateRowFormat(String[] parts) {
        return parts != null && parts.length == 4;
    }
    
    /**
     * Validates that a string can be parsed as an integer.
     * 
     * @param idString the string to validate as an integer
     * @return true if the string can be parsed as an integer, false otherwise
     */
    public static boolean validateProductId(String idString) {
        if (idString == null || idString.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(idString.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validates that a string can be parsed as a BigDecimal price.
     * 
     * @param priceString the string to validate as a price
     * @return true if the string can be parsed as a BigDecimal, false otherwise
     */
    public static boolean validatePrice(String priceString) {
        if (priceString == null || priceString.trim().isEmpty()) {
            return false;
        }
        try {
            new java.math.BigDecimal(priceString.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validates that a product name is not empty or null.
     * 
     * @param name the product name to validate
     * @return true if the name is not null and not empty after trimming, false otherwise
     */
    public static boolean validateProductName(String name) {
        return name != null && !name.trim().isEmpty();
    }
    
    /**
     * Validates that a category is not empty or null.
     * 
     * @param category the category to validate
     * @return true if the category is not null and not empty after trimming, false otherwise
     */
    public static boolean validateCategory(String category) {
        return category != null && !category.trim().isEmpty();
    }
    
    /**
     * Performs comprehensive validation on all product fields.
     * 
     * @param parts the array of CSV field parts
     * @return true if all fields are valid, false otherwise
     */
    public static boolean validateProductData(String[] parts) {
        if (!validateRowFormat(parts)) {
            return false;
        }
        
        return validateProductId(parts[0]) &&
               validateProductName(parts[1]) &&
               validatePrice(parts[2]) &&
               validateCategory(parts[3]);
    }
    
    /**
     * Checks if two strings are equal ignoring case and whitespace.
     * 
     * @param a the first string to compare
     * @param b the second string to compare
     * @return true if the strings are equal ignoring case and whitespace, false otherwise
     */
    public static boolean equalsIgnoreCaseTrim(String a, String b) {
        return a != null && a.trim().equalsIgnoreCase(b);
    }
}
