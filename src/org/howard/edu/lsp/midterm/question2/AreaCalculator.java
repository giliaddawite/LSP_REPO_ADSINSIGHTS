package org.howard.edu.lsp.midterm.question2;

/**
 * AreaCalculator provides overloaded static methods to calculate
 * the area of different shapes: circle, rectangle, triangle, and square.
 */
public class AreaCalculator {
    
    /**
     * Calculate the area of a circle
     * @param radius the radius of the circle
     * @return the area (π × r²)
     * @throws IllegalArgumentException if radius <= 0
     */
    public static double area(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        return Math.PI * radius * radius;
    }
    
    /**
     * Calculate the area of a rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return the area (width × height)
     * @throws IllegalArgumentException if width or height <= 0
     */
    public static double area(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                "Width and height must be positive");
        }
        return width * height;
    }
    
    /**
     * Calculate the area of a triangle
     * @param base the base of the triangle
     * @param height the height of the triangle
     * @return the area (½ × base × height)
     * @throws IllegalArgumentException if base or height <= 0
     */
    public static double area(int base, int height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                "Base and height must be positive");
        }
        return 0.5 * base * height;
    }
    
    /**
     * Calculate the area of a square
     * @param side the side length of the square
     * @return the area (side²)
     * @throws IllegalArgumentException if side <= 0
     */
    public static double area(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive");
        }
        return side * side;
    }
}
