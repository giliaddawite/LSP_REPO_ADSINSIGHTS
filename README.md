# CSCI 363/540 – Assignment 2: CSV ETL Pipeline in Java

## Project Overview
This project implements a simple Extract-Transform-Load (ETL) pipeline in Java that processes CSV product data. The program reads product information from `data/products.csv`, applies various transformations, and outputs the processed data to `data/transformed_products.csv`.

## Design Notes & Assumptions

### Architecture
- **Extract**: Reads CSV file using `BufferedReader` with UTF-8 encoding
- **Transform**: Applies business rules in specific order (uppercase names → discount → recategorization → price range)
- **Load**: Writes transformed data using `BufferedWriter` with proper CSV formatting

### Key Design Decisions
1. **Data Structure**: Used inner classes `Product` and `Counters` for clean data organization
2. **Error Handling**: handling of missing files, empty inputs, and malformed data
3. **Precision**: Used `BigDecimal` with `RoundingMode.HALF_UP` for accurate price calculations
4. **Modularity**: Separated ETL operations into distinct methods for maintainability

### Assumptions
- CSV fields do not contain commas or quotes
- Input file uses UTF-8 encoding
- Price values are valid decimal numbers
- Product names and categories are non-null strings

## How to Run

### Prerequisites
- Java 8 or higher installed
- Project structure with `src/` and `data/` directories

### Execution Steps
1. Navigate to the project root directory (containing `src/` and `data/`)
2. Compile the program:
   ```bash
   javac src/org/howard/edu/lsp/assignment2/ETLPipeline.java
   ```
3. Run the program:
   ```bash
   java -cp src org.howard.edu.lsp.assignment2.ETLPipeline
   ```

### Expected Output
The program will:
- Read from `data/products.csv`
- Apply transformations (uppercase names, 10% discount for Electronics, recategorization, price ranges)
- Write results to `data/transformed_products.csv`
- Display a run summary with statistics

## Testing Strategy

### Test Cases Verified
1. **Normal Input**: 6 products with various categories and prices
   -  Names converted to uppercase
   -  10% discount applied to Electronics
   -  Premium Electronics categorization for high-value Electronics
   -  Price ranges correctly computed

2. **Error Handling**:
   -  Missing input file: Clear error message displayed
   -  Malformed data: Invalid rows skipped and counted
   -  Empty input: Header-only output file created

3. **Edge Cases**:
   -  Zero prices handled correctly
   -  Boundary price ranges (exactly $10, $100, $500)
   -  Case-insensitive category matching

### Sample Test Results
```
---- Run Summary ----
Input path:  data\products.csv
Output path: data\transformed_products.csv
Rows read (excl. header): 6
Transformed:             6
Skipped:                 0
Write successful:        true
```

## AI Usage Documentation

### Summary of AI Assistance
I used AI tools (GPT) to help with:
- Understanding Java file I/O best practices
- Clarifying BigDecimal usage for precise decimal arithmetic
- Reviewing code structure and organization
- Troubleshooting compilation issues

### AI Prompt Example
**Prompt**: "How do I properly handle CSV file reading in Java with error handling for missing files?"

**AI Response**: "Use Files.newBufferedReader() with try-with-resources for automatic cleanup. Check file existence with Files.exists() before attempting to read. Wrap operations in try-catch blocks for IOException handling."

**Usage**: I adapted this approach by implementing the file existence check in the main method and using try-with-resources for both reading and writing operations, ensuring proper resource management and error handling.

### External Sources Used

1. **Java Documentation**
   - Link: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/math/BigDecimal.html
   - Usage: Referenced for BigDecimal arithmetic operations and RoundingMode usage
   - Adaptation: Used setScale() method with HALF_UP rounding for consistent price formatting

2. **Java File I/O Best Practices**
   - Link: https://docs.oracle.com/javase/tutorial/essential/io/
   - Usage: Learned about BufferedReader/BufferedWriter usage patterns
   - Adaptation: Implemented try-with-resources pattern for automatic resource cleanup

## Project Structure
```
JavaProjectRoot/
├── src/
│   └── org/howard/edu/lsp/assignment2/
│       └── ETLPipeline.java
├── data/
│   ├── products.csv (input)
│   └── transformed_products.csv (output)
└── README.md
```

## Verification Checklist
-  Program compiles and runs without modification
-  Uses relative paths for file I/O
-  Correct transformation order implemented
-  Proper error handling for missing/empty files
-  Run summary displayed with accurate statistics
-  Code is well-structured and commented
-  ETL operations separated into distinct methods
-  All requirements from assignment specification met
