package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles data loading to CSV files.
 * This class encapsulates all file writing logic and follows the Single Responsibility Principle.
 * It provides methods for writing transformed product data to CSV files with proper formatting.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class DataLoader {
    
    /** CSV header for the output file */
    private static final String CSV_HEADER = "ProductID,Name,Price,Category,PriceRange";
    
    /**
     * Loads transformed products to the specified output CSV file.
     * 
     * @param products the list of transformed products to write
     * @param outputPath the path to the output CSV file
     * @param statistics the statistics collector to update with write status
     * @return true if the write operation was successful, false otherwise
     */
    public boolean load(List<Product> products, Path outputPath, StatisticsCollector statistics) {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
            // Write CSV header
            writer.write(CSV_HEADER);
            writer.newLine();
            
            // Write each product as a CSV row
            for (Product product : products) {
                writeProductRow(writer, product);
            }
            
            statistics.setWriteSuccessful(true);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error: Failed writing output file: " + e.getMessage());
            statistics.setWriteSuccessful(false);
            return false;
        }
    }
    
    /**
     * Writes a single product as a CSV row.
     * 
     * @param writer the BufferedWriter to write to
     * @param product the product to write
     * @throws IOException if an error occurs while writing
     */
    private void writeProductRow(BufferedWriter writer, Product product) throws IOException {
        // Ensure price is formatted with exactly 2 decimal places
        String formattedPrice = product.getPrice().setScale(2, RoundingMode.HALF_UP).toPlainString();
        
        writer.write(String.format("%d,%s,%s,%s,%s",
            product.getProductId(),
            product.getName(),
            formattedPrice,
            product.getCategory(),
            product.getPriceRange()
        ));
        writer.newLine();
    }
    
    /**
     * Validates that the output directory exists and is writable.
     * 
     * @param outputPath the path to validate
     * @return true if the directory exists and is writable, false otherwise
     */
    public boolean validateOutputDirectory(Path outputPath) {
        try {
            Files.createDirectories(outputPath.getParent());
            return Files.exists(outputPath.getParent()) && Files.isWritable(outputPath.getParent());
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Gets a descriptive error message for output directory issues.
     * 
     * @param e the IOException that occurred
     * @return a descriptive error message
     */
    public String getOutputDirectoryErrorMessage(IOException e) {
        return "Error: Unable to create output directory: " + e.getMessage();
    }
    
    /**
     * Gets the CSV header used in output files.
     * 
     * @return the CSV header string
     */
    public static String getCsvHeader() {
        return CSV_HEADER;
    }
}
