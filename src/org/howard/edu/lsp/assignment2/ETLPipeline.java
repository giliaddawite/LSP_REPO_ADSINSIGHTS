package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
/**
 *  Name: Giliad Dawite
 */
public class ETLPipeline {
    private static final Path INPUT = Paths.get("data/products.csv");
    private static final Path OUTPUT = Paths.get("data/transformed_products.csv"); 

    private static class Counters {
    int rowsRead = 0;
    int transformed = 0;
    int skipped = 0;
}

private static class Product {
    int productId;
    String name;
    BigDecimal price;
    String category;
    String priceRange;

    Product(int id, String name, BigDecimal price, String category) {
        this.productId = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public static void main(String[] args) {
    if (!Files.exists(INPUT)) {
        System.err.println("Error: Missing input file at " + INPUT.toString() + ". Expected project structure places products.csv under data/.");
        return;
    }

    try {
        Files.createDirectories(OUTPUT.getParent());
    } catch (IOException e) {
        System.err.println("Error: Unable to create output directory: " + e.getMessage());
        return;
    }

    Counters counters = new Counters();
    ArrayList<Product> extracted = extract(INPUT, counters);
    ArrayList<Product> transformed = transform(extracted, counters);
    boolean wrote = load(transformed, OUTPUT);

    // 4) Print run summary
    System.out.println("---- Run Summary ----");
    System.out.println("Input path:  " + INPUT.toString());
    System.out.println("Output path: " + OUTPUT.toString());
    System.out.println("Rows read (excl. header): " + counters.rowsRead);
    System.out.println("Transformed:             " + counters.transformed);
    System.out.println("Skipped:                 " + counters.skipped);
    System.out.println("Write successful:        " + wrote);
}


private static ArrayList<Product> extract(Path inputPath, Counters counters) {
    ArrayList<Product> products = new ArrayList<>();
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

            counters.rowsRead++;
            String[] parts = line.split(",", -1);
            if (parts.length != 4) {
                counters.skipped++;
                continue;
            }

            try {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                BigDecimal price = new BigDecimal(parts[2].trim());
                String category = parts[3].trim();
                products.add(new Product(id, name, price, category));
            } catch (NumberFormatException ex) {
                counters.skipped++;
            }
        }
    } catch (IOException e) {
        System.err.println("Error: Failed reading input file: " + e.getMessage());
    }
    return products;
}

/**
 * TRANSFORM: For each product:
 *  (1) Uppercase name
 *  (2) 10% discount & round HALF_UP if original category == Electronics
 *  (3) If post-discount price > 500.00 AND original category == Electronics -> "Premium Electronics"
 *  (4) Compute PriceRange from FINAL price
 */
private static ArrayList<Product> transform(ArrayList<Product> input, Counters counters) {
    ArrayList<Product> out = new ArrayList<>();
    for (Product p : input) {
        String originalCategory = p.category; // needed for rule (3)

        // (1) Uppercase name
        p.name = p.name.toUpperCase(Locale.ROOT);

        // Normalize incoming price scale (not required, but nice)
        p.price = p.price.setScale(2, RoundingMode.HALF_UP);

        // (2) Discount 10% if Electronics
        if (equalsIgnoreCaseTrim(originalCategory, "Electronics")) {
            BigDecimal discounted = p.price.multiply(new BigDecimal("0.90"));
            p.price = discounted.setScale(2, RoundingMode.HALF_UP);
        }

        // (3) Re-categorize if final price > 500 AND original category was Electronics
        if (equalsIgnoreCaseTrim(originalCategory, "Electronics")
                && p.price.compareTo(new BigDecimal("500.00")) > 0) {
            p.category = "Premium Electronics";
        } else {
            // Keep existing (already in p.category)
        }

        // (4) PriceRange from final price
        p.priceRange = computePriceRange(p.price);

        out.add(p);
        counters.transformed++;
    }
    return out;
}

private static boolean equalsIgnoreCaseTrim(String a, String b) {
    return a != null && a.trim().equalsIgnoreCase(b);
}

private static String computePriceRange(BigDecimal price) {
    int cmpTo10  = price.compareTo(new BigDecimal("10.00"));
    int cmpTo100 = price.compareTo(new BigDecimal("100.00"));
    int cmpTo500 = price.compareTo(new BigDecimal("500.00"));

    if (cmpTo10 <= 0) return "Low";
    if (cmpTo100 <= 0) return "Medium";
    if (cmpTo500 <= 0) return "High";
    return "Premium";
}

private static boolean load(ArrayList<Product> rows, Path outputPath) {
    try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
        writer.write("ProductID,Name,Price,Category,PriceRange");
        writer.newLine();

        for (Product p : rows) {
            // Ensure price is 2 decimals when printing
            BigDecimal printed = p.price.setScale(2, RoundingMode.HALF_UP);
            writer.write(
                p.productId + "," +
                p.name + "," +
                printed.toPlainString() + "," +
                p.category + "," +
                p.priceRange
            );
            writer.newLine();
        }
        return true;
    } catch (IOException e) {
        System.err.println("Error: Failed writing output file: " + e.getMessage());
        return false;
    }
}
}