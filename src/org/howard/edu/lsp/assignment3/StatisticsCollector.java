package org.howard.edu.lsp.assignment3;

/**
 * Collects and manages statistics for the ETL pipeline operations.
 * This class encapsulates all counting and metrics-related functionality,
 * providing a clean interface for tracking pipeline performance.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class StatisticsCollector {
    
    /** Number of rows read from input file (excluding header) */
    private int rowsRead;
    
    /** Number of products successfully transformed */
    private int transformed;
    
    /** Number of products skipped due to validation errors */
    private int skipped;
    
    /** Whether the write operation was successful */
    private boolean writeSuccessful;
    
    /**
     * Constructs a new StatisticsCollector with all counters initialized to zero.
     */
    public StatisticsCollector() {
        this.rowsRead = 0;
        this.transformed = 0;
        this.skipped = 0;
        this.writeSuccessful = false;
    }
    
    /**
     * Increments the rows read counter.
     */
    public void incrementRowsRead() {
        this.rowsRead++;
    }
    
    /**
     * Increments the transformed counter.
     */
    public void incrementTransformed() {
        this.transformed++;
    }
    
    /**
     * Increments the skipped counter.
     */
    public void incrementSkipped() {
        this.skipped++;
    }
    
    /**
     * Sets the write operation success status.
     * 
     * @param successful true if write was successful, false otherwise
     */
    public void setWriteSuccessful(boolean successful) {
        this.writeSuccessful = successful;
    }
    
    /**
     * Gets the number of rows read.
     * 
     * @return the number of rows read
     */
    public int getRowsRead() {
        return rowsRead;
    }
    
    /**
     * Gets the number of products transformed.
     * 
     * @return the number of products transformed
     */
    public int getTransformed() {
        return transformed;
    }
    
    /**
     * Gets the number of products skipped.
     * 
     * @return the number of products skipped
     */
    public int getSkipped() {
        return skipped;
    }
    
    /**
     * Gets the write operation success status.
     * 
     * @return true if write was successful, false otherwise
     */
    public boolean isWriteSuccessful() {
        return writeSuccessful;
    }
    
    /**
     * Resets all counters to zero.
     */
    public void reset() {
        this.rowsRead = 0;
        this.transformed = 0;
        this.skipped = 0;
        this.writeSuccessful = false;
    }
    
    /**
     * Returns a summary of all collected statistics.
     * 
     * @return a formatted string containing all statistics
     */
    public String getSummary() {
        return String.format("Rows read: %d, Transformed: %d, Skipped: %d, Write successful: %b",
                rowsRead, transformed, skipped, writeSuccessful);
    }
}
