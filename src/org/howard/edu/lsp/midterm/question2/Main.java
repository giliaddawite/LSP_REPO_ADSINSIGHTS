package org.howard.edu.lsp.midterm.question2;

/**
 * Main class to demonstrate the AreaCalculator overloaded methods.
 * 
 * RATIONALE ON OVERLOADING:
 * Method overloading is the better design choice here because all methods
 * perform the same conceptual operation (calculating area) but on different
 * shapes. Using the same method name 'area()' makes the API more intuitive
 * and easier to remember compared to separate names like rectangleArea(),
 * circleArea(), etc. This demonstrates polymorphism and provides a cleaner,
 * more unified interface for users of the class.
 */
public class Main {
    public static void main(String[] args) {
        // Test circle area
        double circleRadius = 3.0;
        double circleArea = AreaCalculator.area(circleRadius);
        System.out.println("Circle radius " + circleRadius + 
                           " → area = " + circleArea);
        
        // Test rectangle area
        double rectWidth = 5.0;
        double rectHeight = 2.0;
        double rectArea = AreaCalculator.area(rectWidth, rectHeight);
        System.out.println("Rectangle " + rectWidth + " x " + rectHeight + 
                           " → area = " + rectArea);
        
        // Test triangle area
        int triBase = 10;
        int triHeight = 6;
        double triArea = AreaCalculator.area(triBase, triHeight);
        System.out.println("Triangle base " + triBase + ", height " + 
                           triHeight + " → area = " + triArea);
        
        // Test square area
        int squareSide = 4;
        double squareArea = AreaCalculator.area(squareSide);
        System.out.println("Square side " + squareSide + 
                           " → area = " + squareArea);
        
        // Test exception handling
        System.out.println("\n=== Testing exception handling ===");
        try {
            AreaCalculator.area(-5.0);
            System.out.println("ERROR: Exception should have been thrown!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
