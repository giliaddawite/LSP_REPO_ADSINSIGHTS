package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Calculates price ranges based on product prices.
 * This class encapsulates the business logic for determining price ranges,
 * following the Single Responsibility Principle.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class PriceRangeCalculator {
    
    /** Price threshold for Low range */
    private static final BigDecimal LOW_THRESHOLD = new BigDecimal("10.00");
    
    /** Price threshold for Medium range */
    private static final BigDecimal MEDIUM_THRESHOLD = new BigDecimal("100.00");
    
    /** Price threshold for High range */
    private static final BigDecimal HIGH_THRESHOLD = new BigDecimal("500.00");
    
    /**
     * Calculates the appropriate price range based on the given price.
     * Price ranges are determined as follows:
     * - Low: price <= $10.00
     * - Medium: $10.00 < price <= $100.00
     * - High: $100.00 < price <= $500.00
     * - Premium: price > $500.00
     * 
     * @param price the price to evaluate
     * @return the calculated price range as a String
     */
    public static String calculatePriceRange(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        
        if (price.compareTo(LOW_THRESHOLD) <= 0) {
            return "Low";
        } else if (price.compareTo(MEDIUM_THRESHOLD) <= 0) {
            return "Medium";
        } else if (price.compareTo(HIGH_THRESHOLD) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
    
    /**
     * Gets the low price threshold.
     * 
     * @return the low price threshold
     */
    public static BigDecimal getLowThreshold() {
        return LOW_THRESHOLD;
    }
    
    /**
     * Gets the medium price threshold.
     * 
     * @return the medium price threshold
     */
    public static BigDecimal getMediumThreshold() {
        return MEDIUM_THRESHOLD;
    }
    
    /**
     * Gets the high price threshold.
     * 
     * @return the high price threshold
     */
    public static BigDecimal getHighThreshold() {
        return HIGH_THRESHOLD;
    }
}
