package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;


public class RangeContains {
	private static Range range;

    @BeforeClass
    public static void setup () {
        range = new Range (-5, 15);
    }

    @AfterClass
    public static void teardown () {
        range = null;
    }

    @Test
    public void testContainsPositiveNumber () {
    	// This test is testing partition of positive numbers within the range
        boolean res = range.contains(5.0);
        assertTrue (res);
    }

    @Test
    public void testContainsNegativeNumber () {
    	// This test is testing partition of negative numbers within the range
    	boolean res = range.contains(-1);
    	assertTrue (res);
    }
    
    @Test
    public void testContainsPositiveValueOutsideTheRange () {
    	// This test is testing partition of positive numbers out of range
    	boolean res = range.contains(100);
    	assertFalse (res);
    }
    
    @Test
    public void testContainsNegativeValueOutsideTheRange () {
    	// This test is testing partition of negative numbers out of range
        boolean res = range.contains(-100);
        assertFalse (res);
    }
    
    @Test
    public void testContainsValueUpperBound () {
    	// This test is testing partition of numbers at the upper bound
    	boolean res = range.contains(15);
    	assertTrue (res);
    }
    
    @Test
    public void testContainsValueLowerBound () {
    	// This test is testing partition of numbers at the lower bound
        boolean res = range.contains(-5);
        assertTrue (res);
    }
}