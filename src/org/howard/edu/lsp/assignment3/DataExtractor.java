package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles data extraction from CSV files.
 * This class encapsulates all file reading logic and follows the Single Responsibility Principle.
 * It provides methods for extracting product data from CSV files with proper error handling.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class DataExtractor {
    
    /**
     * Extracts product data from the specified CSV file.
     * 
     * @param inputPath the path to the input CSV file
     * @param statistics the statistics collector to update during extraction
     * @return a list of extracted products
     * @throws IOException if an error occurs while reading the file
     */
    public List<Product> extract(Path inputPath, StatisticsCollector statistics) throws IOException {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                statistics.incrementRowsRead();
                
                String[] parts = line.split(",", -1);
                
                if (!ProductValidator.validateProductData(parts)) {
                    statistics.incrementSkipped();
                    continue;
                }
                
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();
                    
                    products.add(new Product(id, name, price, category));
                } catch (NumberFormatException e) {
                    statistics.incrementSkipped();
                }
            }
        }
        
        return products;
    }
    
    /**
     * Validates that the input file exists and is readable.
     * 
     * @param inputPath the path to validate
     * @return true if the file exists and is readable, false otherwise
     */
    public boolean validateInputFile(Path inputPath) {
        return Files.exists(inputPath) && Files.isReadable(inputPath);
    }
    
    /**
     * Gets a descriptive error message for missing input files.
     * 
     * @param inputPath the path that was missing
     * @return a descriptive error message
     */
    public String getMissingFileErrorMessage(Path inputPath) {
        return "Error: Missing input file at " + inputPath.toString() + 
               ". Expected project structure places products.csv under data/.";
    }
}
