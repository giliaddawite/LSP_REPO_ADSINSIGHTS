package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * JUnit test class for IntegerSet.
 * Tests all methods including normal cases, edge cases, and exceptions.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class IntegerSetTest {
    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;
    
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        emptySet = new IntegerSet();
    }
    
    // ========== Group A: Simple Operations ==========
    
    @Test
    @DisplayName("Test clear() removes all elements")
    public void testClear() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Act
        set1.clear();
        
        // Assert
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }
    
    @Test
    @DisplayName("Test clear() on empty set")
    public void testClearEmptySet() {
        // Act
        emptySet.clear();
        
        // Assert
        assertTrue(emptySet.isEmpty());
    }
    
    @Test
    @DisplayName("Test length() returns correct size")
    public void testLength() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Assert
        assertEquals(3, set1.length());
    }
    
    @Test
    @DisplayName("Test length() on empty set")
    public void testLengthEmptySet() {
        assertEquals(0, emptySet.length());
    }
    
    @Test
    @DisplayName("Test isEmpty() returns true for empty set")
    public void testIsEmptyTrue() {
        assertTrue(emptySet.isEmpty());
    }
    
    @Test
    @DisplayName("Test isEmpty() returns false for non-empty set")
    public void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }
    
    @Test
    @DisplayName("Test contains() returns true when value exists")
    public void testContainsTrue() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Assert
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }
    
    @Test
    @DisplayName("Test contains() returns false when value doesn't exist")
    public void testContainsFalse() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        // Assert
        assertFalse(set1.contains(3));
        assertFalse(set1.contains(0));
    }
    
    @Test
    @DisplayName("Test contains() on empty set")
    public void testContainsEmptySet() {
        assertFalse(emptySet.contains(1));
    }
    
    @Test
    @DisplayName("Test toString() returns correct format")
    public void testToString() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Act
        String result = set1.toString();
        
        // Assert
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }
    
    @Test
    @DisplayName("Test toString() on empty set")
    public void testToStringEmptySet() {
        assertEquals("[]", emptySet.toString());
    }
    
    // ========== Group B: Mutation ==========
    
    @Test
    @DisplayName("Test add() adds new element")
    public void testAdd() {
        // Act
        set1.add(1);
        
        // Assert
        assertTrue(set1.contains(1));
        assertEquals(1, set1.length());
    }
    
    @Test
    @DisplayName("Test add() doesn't add duplicate")
    public void testAddDuplicate() {
        // Arrange
        set1.add(1);
        int initialLength = set1.length();
        
        // Act
        set1.add(1);
        
        // Assert
        assertEquals(initialLength, set1.length());
        assertEquals(1, set1.length());
    }
    
    @Test
    @DisplayName("Test add() with multiple unique elements")
    public void testAddMultiple() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }
    
    @Test
    @DisplayName("Test remove() removes existing element")
    public void testRemove() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Act
        set1.remove(2);
        
        // Assert
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(3));
    }
    
    @Test
    @DisplayName("Test remove() on non-existent element")
    public void testRemoveNonExistent() {
        // Arrange
        set1.add(1);
        set1.add(2);
        int initialLength = set1.length();
        
        // Act
        set1.remove(3);
        
        // Assert
        assertEquals(initialLength, set1.length());
    }
    
    @Test
    @DisplayName("Test remove() on empty set")
    public void testRemoveEmptySet() {
        emptySet.remove(1);
        assertTrue(emptySet.isEmpty());
    }
    
    // ========== Group C: Extremes ==========
    
    @Test
    @DisplayName("Test largest() returns maximum value")
    public void testLargest() {
        // Arrange
        set1.add(5);
        set1.add(2);
        set1.add(8);
        set1.add(1);
        
        // Act
        int result = set1.largest();
        
        // Assert
        assertEquals(8, result);
    }
    
    @Test
    @DisplayName("Test largest() with negative numbers")
    public void testLargestNegative() {
        set1.add(-5);
        set1.add(-2);
        set1.add(-8);
        
        assertEquals(-2, set1.largest());
    }
    
    @Test
    @DisplayName("Test largest() throws exception on empty set")
    public void testLargestThrowsException() {
        assertThrows(IllegalStateException.class, () -> emptySet.largest());
    }
    
    @Test
    @DisplayName("Test largest() with single element")
    public void testLargestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.largest());
    }
    
    @Test
    @DisplayName("Test smallest() returns minimum value")
    public void testSmallest() {
        // Arrange
        set1.add(5);
        set1.add(2);
        set1.add(8);
        set1.add(1);
        
        // Act
        int result = set1.smallest();
        
        // Assert
        assertEquals(1, result);
    }
    
    @Test
    @DisplayName("Test smallest() with negative numbers")
    public void testSmallestNegative() {
        set1.add(-5);
        set1.add(-2);
        set1.add(-8);
        
        assertEquals(-8, set1.smallest());
    }
    
    @Test
    @DisplayName("Test smallest() throws exception on empty set")
    public void testSmallestThrowsException() {
        assertThrows(IllegalStateException.class, () -> emptySet.smallest());
    }
    
    @Test
    @DisplayName("Test smallest() with single element")
    public void testSmallestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.smallest());
    }
    
    // ========== Group D: Comparison ==========
    
    @Test
    @DisplayName("Test equals() returns true for identical sets")
    public void testEqualsTrue() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(1);
        set2.add(2);
        set2.add(3);
        
        // Assert
        assertTrue(set1.equals(set2));
        assertTrue(set2.equals(set1));
    }
    
    @Test
    @DisplayName("Test equals() returns true for sets with same elements in different order")
    public void testEqualsDifferentOrder() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(3);
        set2.add(1);
        set2.add(2);
        
        // Assert
        assertTrue(set1.equals(set2));
    }
    
    @Test
    @DisplayName("Test equals() returns false for different sets")
    public void testEqualsFalse() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(1);
        set2.add(2);
        set2.add(4);
        
        // Assert
        assertFalse(set1.equals(set2));
    }
    
    @Test
    @DisplayName("Test equals() returns false for sets of different sizes")
    public void testEqualsDifferentSizes() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(2);
        set2.add(3);
        
        // Assert
        assertFalse(set1.equals(set2));
    }
    
    @Test
    @DisplayName("Test equals() returns true for empty sets")
    public void testEqualsEmptySets() {
        assertTrue(emptySet.equals(new IntegerSet()));
    }
    
    @Test
    @DisplayName("Test equals() returns false for null")
    public void testEqualsNull() {
        assertFalse(set1.equals(null));
    }
    
    @Test
    @DisplayName("Test equals() returns false for non-IntegerSet object")
    public void testEqualsWrongType() {
        set1.add(1);
        assertFalse(set1.equals("not a set"));
        assertFalse(set1.equals(123));
    }
    
    // ========== Group E: Set Theory ==========
    
    @Test
    @DisplayName("Test union() adds all unique elements from other set")
    public void testUnion() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(3);
        set2.add(4);
        set2.add(5);
        
        // Act
        set1.union(set2);
        
        // Assert
        assertEquals(5, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertTrue(set1.contains(5));
    }
    
    @Test
    @DisplayName("Test union() with empty set")
    public void testUnionWithEmptySet() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        // Act
        set1.union(emptySet);
        
        // Assert
        assertEquals(2, set1.length());
    }
    
    @Test
    @DisplayName("Test union() empty set with non-empty set")
    public void testUnionEmptyWithNonEmpty() {
        // Arrange
        set2.add(1);
        set2.add(2);
        
        // Act
        emptySet.union(set2);
        
        // Assert
        assertEquals(2, emptySet.length());
        assertTrue(emptySet.contains(1));
        assertTrue(emptySet.contains(2));
    }
    
    @Test
    @DisplayName("Test intersect() keeps only common elements")
    public void testIntersect() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        
        set2.add(2);
        set2.add(3);
        set2.add(5);
        
        // Act
        set1.intersect(set2);
        
        // Assert
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(4));
    }
    
    @Test
    @DisplayName("Test intersect() with no common elements")
    public void testIntersectNoCommon() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        // Act
        set1.intersect(set2);
        
        // Assert
        assertTrue(set1.isEmpty());
    }
    
    @Test
    @DisplayName("Test intersect() with empty set")
    public void testIntersectWithEmptySet() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        // Act
        set1.intersect(emptySet);
        
        // Assert
        assertTrue(set1.isEmpty());
    }
    
    @Test
    @DisplayName("Test diff() removes elements in other set")
    public void testDiff() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        
        set2.add(2);
        set2.add(3);
        set2.add(5);
        
        // Act
        set1.diff(set2);
        
        // Assert
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(3));
    }
    
    @Test
    @DisplayName("Test diff() with no overlapping elements")
    public void testDiffNoOverlap() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        // Act
        set1.diff(set2);
        
        // Assert
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }
    
    @Test
    @DisplayName("Test diff() with empty set")
    public void testDiffWithEmptySet() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        // Act
        set1.diff(emptySet);
        
        // Assert
        assertEquals(2, set1.length());
    }
    
    @Test
    @DisplayName("Test diff() removes all elements when other contains all")
    public void testDiffRemovesAll() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(2);
        
        // Act
        set1.diff(set2);
        
        // Assert
        assertTrue(set1.isEmpty());
    }
    
    @Test
    @DisplayName("Test complement() makes this = other - this")
    public void testComplement() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set2.add(5);
        
        // Act
        set1.complement(set2);
        
        // Assert
        assertEquals(2, set1.length());
        assertTrue(set1.contains(4));
        assertTrue(set1.contains(5));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(3));
    }
    
    @Test
    @DisplayName("Test complement() when this contains all of other")
    public void testComplementThisContainsAll() {
        // Arrange
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(1);
        set2.add(2);
        
        // Act
        set1.complement(set2);
        
        // Assert
        assertTrue(set1.isEmpty());
    }
    
    @Test
    @DisplayName("Test complement() when this and other are disjoint")
    public void testComplementDisjoint() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        // Act
        set1.complement(set2);
        
        // Assert
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
    }
    
    @Test
    @DisplayName("Test complement() with empty this set")
    public void testComplementEmptyThis() {
        // Arrange
        set2.add(1);
        set2.add(2);
        set2.add(3);
        
        // Act
        emptySet.complement(set2);
        
        // Assert
        assertEquals(3, emptySet.length());
        assertTrue(emptySet.contains(1));
        assertTrue(emptySet.contains(2));
        assertTrue(emptySet.contains(3));
    }
    
    @Test
    @DisplayName("Test complement() with empty other set")
    public void testComplementEmptyOther() {
        // Arrange
        set1.add(1);
        set1.add(2);
        
        // Act
        set1.complement(emptySet);
        
        // Assert
        assertTrue(set1.isEmpty());
    }
}

