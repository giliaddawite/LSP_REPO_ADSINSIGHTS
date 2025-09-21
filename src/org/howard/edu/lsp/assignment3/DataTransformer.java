package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Handles data transformation according to business rules.
 * This class encapsulates all transformation logic and follows the Single Responsibility Principle.
 * It applies the following transformation rules:
 * 1. Uppercase product names
 * 2. Apply 10% discount for Electronics category
 * 3. Re-categorize expensive Electronics as "Premium Electronics"
 * 4. Calculate price ranges based on final prices
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class DataTransformer {
    
    /** Discount rate for Electronics (10%) */
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.90");
    
    /** Premium Electronics threshold */
    private static final BigDecimal PREMIUM_THRESHOLD = new BigDecimal("500.00");
    
    /** Electronics category name */
    private static final String ELECTRONICS_CATEGORY = "Electronics";
    
    /** Premium Electronics category name */
    private static final String PREMIUM_ELECTRONICS_CATEGORY = "Premium Electronics";
    
    /**
     * Transforms a list of products according to business rules.
     * 
     * @param inputProducts the list of products to transform
     * @param statistics the statistics collector to update during transformation
     * @return a list of transformed products
     */
    public List<Product> transform(List<Product> inputProducts, StatisticsCollector statistics) {
        List<Product> transformedProducts = new ArrayList<>();
        
        for (Product product : inputProducts) {
            Product transformedProduct = transformProduct(product);
            transformedProducts.add(transformedProduct);
            statistics.incrementTransformed();
        }
        
        return transformedProducts;
    }
    
    /**
     * Transforms a single product according to business rules.
     * 
     * @param product the product to transform
     * @return the transformed product
     */
    private Product transformProduct(Product product) {
        // Create a copy to avoid modifying the original
        Product transformed = new Product(
            product.getProductId(),
            product.getName(),
            product.getPrice(),
            product.getCategory()
        );
        
        // Rule 1: Uppercase name
        transformed.setName(transformed.getName().toUpperCase(Locale.ROOT));
        
        // Normalize price scale
        transformed.setPrice(transformed.getPrice().setScale(2, RoundingMode.HALF_UP));
        
        // Store original category for rule 3
        String originalCategory = transformed.getCategory();
        
        // Rule 2: Apply 10% discount for Electronics
        if (ProductValidator.equalsIgnoreCaseTrim(originalCategory, ELECTRONICS_CATEGORY)) {
            BigDecimal discountedPrice = transformed.getPrice().multiply(DISCOUNT_RATE);
            transformed.setPrice(discountedPrice.setScale(2, RoundingMode.HALF_UP));
        }
        
        // Rule 3: Re-categorize expensive Electronics as Premium Electronics
        if (ProductValidator.equalsIgnoreCaseTrim(originalCategory, ELECTRONICS_CATEGORY) &&
            transformed.getPrice().compareTo(PREMIUM_THRESHOLD) > 0) {
            transformed.setCategory(PREMIUM_ELECTRONICS_CATEGORY);
        }
        
        // Rule 4: Calculate price range from final price
        transformed.setPriceRange(PriceRangeCalculator.calculatePriceRange(transformed.getPrice()));
        
        return transformed;
    }
    
    /**
     * Gets the discount rate used for Electronics.
     * 
     * @return the discount rate
     */
    public static BigDecimal getDiscountRate() {
        return DISCOUNT_RATE;
    }
    
    /**
     * Gets the premium threshold for Electronics.
     * 
     * @return the premium threshold
     */
    public static BigDecimal getPremiumThreshold() {
        return PREMIUM_THRESHOLD;
    }
    
    /**
     * Gets the Electronics category name.
     * 
     * @return the Electronics category name
     */
    public static String getElectronicsCategory() {
        return ELECTRONICS_CATEGORY;
    }
    
    /**
     * Gets the Premium Electronics category name.
     * 
     * @return the Premium Electronics category name
     */
    public static String getPremiumElectronicsCategory() {
        return PREMIUM_ELECTRONICS_CATEGORY;
    }
}
