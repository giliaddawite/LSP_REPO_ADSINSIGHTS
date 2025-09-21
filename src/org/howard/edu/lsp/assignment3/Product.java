package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product in the ETL pipeline with proper encapsulation.
 * This class encapsulates all product-related data and provides controlled access
 * through getter and setter methods.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class Product {
    
    /** The unique identifier for the product */
    private int productId;
    
    /** The name of the product */
    private String name;
    
    /** The price of the product */
    private BigDecimal price;
    
    /** The category of the product */
    private String category;
    
    /** The calculated price range based on the final price */
    private String priceRange;
    
    /**
     * Constructs a new Product with the specified parameters.
     * 
     * @param productId the unique identifier for the product
     * @param name the name of the product
     * @param price the price of the product
     * @param category the category of the product
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = null; // Will be set during transformation
    }
    
    /**
     * Gets the product ID.
     * 
     * @return the product ID
     */
    public int getProductId() {
        return productId;
    }
    
    /**
     * Sets the product ID.
     * 
     * @param productId the product ID to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the product name.
     * 
     * @param name the product name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the product price.
     * 
     * @return the product price
     */
    public BigDecimal getPrice() {
        return price;
    }
    
    /**
     * Sets the product price.
     * 
     * @param price the product price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    /**
     * Gets the product category.
     * 
     * @return the product category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets the product category.
     * 
     * @param category the product category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Gets the calculated price range.
     * 
     * @return the price range
     */
    public String getPriceRange() {
        return priceRange;
    }
    
    /**
     * Sets the calculated price range.
     * 
     * @param priceRange the price range to set
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
    
    /**
     * Returns a string representation of the product for debugging purposes.
     * 
     * @return a string representation of the product
     */
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", priceRange='" + priceRange + '\'' +
                '}';
    }
}
