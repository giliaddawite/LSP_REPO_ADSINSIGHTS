package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Main ETL pipeline orchestrator that coordinates the entire Extract-Transform-Load process.
 * This class follows the Facade pattern, providing a simple interface to the complex ETL operations.
 * It demonstrates composition by using specialized classes for each phase of the pipeline.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class ETLPipeline {
    
    /** Path to the input CSV file */
    private static final Path INPUT_PATH = Paths.get("data/products.csv");
    
    /** Path to the output CSV file */
    private static final Path OUTPUT_PATH = Paths.get("data/transformed_products.csv");
    
    /** Data extractor component */
    private final DataExtractor extractor;
    
    /** Data transformer component */
    private final DataTransformer transformer;
    
    /** Data loader component */
    private final DataLoader loader;
    
    /** Statistics collector for tracking pipeline metrics */
    private final StatisticsCollector statistics;
    
    /**
     * Constructs a new ETLPipeline with all necessary components.
     * This constructor demonstrates composition - the pipeline is composed of
     * specialized components that handle specific responsibilities.
     */
    public ETLPipeline() {
        this.extractor = new DataExtractor();
        this.transformer = new DataTransformer();
        this.loader = new DataLoader();
        this.statistics = new StatisticsCollector();
    }
    
    /**
     * Executes the complete ETL pipeline process.
     * This method orchestrates the three main phases: Extract, Transform, and Load.
     * 
     * @return true if the entire pipeline executed successfully, false otherwise
     */
    public boolean execute() {
        try {
            // Phase 1: Extract - Validate input and extract data
            if (!extractor.validateInputFile(INPUT_PATH)) {
                System.err.println(extractor.getMissingFileErrorMessage(INPUT_PATH));
                return false;
            }
            
            // Phase 2: Extract - Read data from CSV file
            List<Product> extractedProducts = extractor.extract(INPUT_PATH, statistics);
            
            // Phase 3: Transform - Apply business rules
            List<Product> transformedProducts = transformer.transform(extractedProducts, statistics);
            
            // Phase 4: Load - Validate output directory and write results
            if (!loader.validateOutputDirectory(OUTPUT_PATH)) {
                System.err.println(loader.getOutputDirectoryErrorMessage(new IOException("Directory validation failed")));
                return false;
            }
            
            // Phase 5: Load - Write transformed data to output file
            boolean writeSuccess = loader.load(transformedProducts, OUTPUT_PATH, statistics);
            
            // Phase 6: Report - Print execution summary
            printExecutionSummary();
            
            return writeSuccess;
            
        } catch (IOException e) {
            System.err.println("Error: Failed reading input file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Prints a summary of the pipeline execution results.
     * This method demonstrates encapsulation by providing a clean interface
     * to access statistics without exposing internal details.
     */
    private void printExecutionSummary() {
        System.out.println("---- Run Summary ----");
        System.out.println("Input path:  " + INPUT_PATH.toString());
        System.out.println("Output path: " + OUTPUT_PATH.toString());
        System.out.println("Rows read (excl. header): " + statistics.getRowsRead());
        System.out.println("Transformed:             " + statistics.getTransformed());
        System.out.println("Skipped:                 " + statistics.getSkipped());
        System.out.println("Write successful:        " + statistics.isWriteSuccessful());
    }
    
    /**
     * Gets the current statistics from the pipeline execution.
     * 
     * @return the statistics collector containing pipeline metrics
     */
    public StatisticsCollector getStatistics() {
        return statistics;
    }
    
    /**
     * Gets the input path used by the pipeline.
     * 
     * @return the input path
     */
    public static Path getInputPath() {
        return INPUT_PATH;
    }
    
    /**
     * Gets the output path used by the pipeline.
     * 
     * @return the output path
     */
    public static Path getOutputPath() {
        return OUTPUT_PATH;
    }
    
    /**
     * Main method that serves as the entry point for the ETL pipeline application.
     * This method demonstrates polymorphism by creating an ETLPipeline instance
     * and calling its execute method, which could be overridden in subclasses.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        boolean success = pipeline.execute();
        
        if (success) {
            System.out.println("ETL pipeline completed successfully.");
        } else {
            System.err.println("ETL pipeline completed with errors.");
            System.exit(1);
        }
    }
}
