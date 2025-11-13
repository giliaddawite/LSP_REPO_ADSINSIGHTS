package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A mathematical set of integers that stores unique integer values.
 * This class provides standard set operations including union, intersection,
 * difference, and complement.
 * 
 * @author Giliad Dawite
 * @version 1.0
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();
    
    /**
     * Clears the internal representation of the set.
     */
    public void clear() {
        set.clear();
    }
    
    /**
     * Returns the number of elements in the set.
     * 
     * @return the size of the set
     */
    public int length() {
        return set.size();
    }
    
    /**
     * Returns true if the 2 sets are equal, false otherwise;
     * Two sets are equal if they contain all of the same values in ANY order.
     * This overrides the equals method from the Object class.
     * 
     * @param o the object to compare with
     * @return true if the sets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof IntegerSet)) {
            return false;
        }
        IntegerSet other = (IntegerSet) o;
        if (this.length() != other.length()) {
            return false;
        }
        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }
    
    /**
     * Returns true if the set contains the value, otherwise false.
     * 
     * @param value the integer value to check for
     * @return true if the set contains the value, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }
    
    /**
     * Returns the largest item in the set (throws IllegalStateException if empty).
     * 
     * @return the maximum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }
    
    /**
     * Returns the smallest item in the set (throws IllegalStateException if empty).
     * 
     * @return the minimum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }
    
    /**
     * Adds an item to the set or does nothing if already present.
     * 
     * @param item the integer value to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }
    
    /**
     * Removes an item from the set or does nothing if not there.
     * 
     * @param item the integer value to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }
    
    /**
     * Set union: modifies this to contain all unique elements in this or other.
     * 
     * @param other the other IntegerSet to union with
     */
    public void union(IntegerSet other) {
        for (int i = 0; i < other.length(); i++) {
            int value = other.set.get(i);
            if (!this.set.contains(value)) {
                this.set.add(value);
            }
        }
    }
    
    /**
     * Set intersection: modifies this to contain only elements in both sets.
     * 
     * @param other the other IntegerSet to intersect with
     */
    public void intersect(IntegerSet other) {
        this.set.retainAll(other.set);
    }
    
    /**
     * Set difference (this \ other): modifies this to remove elements found in other.
     * 
     * @param other the other IntegerSet to subtract from this set
     */
    public void diff(IntegerSet other) {
        this.set.removeAll(other.set);
    }
    
    /**
     * Set complement: modifies this to become (other \ this).
     * 
     * @param other the other IntegerSet to complement with
     */
    public void complement(IntegerSet other) {
        ArrayList<Integer> originalElements = new ArrayList<Integer>(this.set);
        this.set.clear();
        for (int i = 0; i < other.length(); i++) {
            int value = other.set.get(i);
            if (!originalElements.contains(value)) {
                this.set.add(value);
            }
        }
    }
    
    /**
     * Returns true if the set is empty, false otherwise.
     * 
     * @return true if the set contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }
    
    /**
     * Returns a String representation; overrides Object.toString().
     * 
     * @return a string representation of the set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}

