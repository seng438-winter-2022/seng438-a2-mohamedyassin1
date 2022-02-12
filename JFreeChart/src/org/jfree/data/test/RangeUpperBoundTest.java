package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

/*
 * This class is to test the getUpperBound() method using equivalence class testing and boundary value testing.
 */
public class RangeUpperBoundTest {
    private Range exampleRange;
    private Range UpperBoundZero;
    private Range UpperBoundPositive;
    private Range UpperBoundNegative;
    private Range UpperBoundChar;
    private Range UpperBoundMax;
    private Range UpperBoundOverMax;
    // private Range RangeReversed;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
    	UpperBoundZero = new Range(-1, 0);
        UpperBoundPositive = new Range(1, 20.1);
        UpperBoundNegative = new Range(-20, -1);
        UpperBoundChar = new Range(0, 'a');
        UpperBoundMax = new Range(1, Double.MAX_VALUE);
        UpperBoundOverMax = new Range (0, Double.MAX_VALUE + 1000000000000000000000000000000000000000000.0);
        
        // Reversing range does not work since the function requires lower < upper
        // RangeReversed = new Range (10, 1);
    }

    /* 
     * This test covers the upper bound value of 0 with an arbitrary lower bound.
     */
    @Test
    public void testUpperBoundZero() {
        assertEquals("The upper bound value of the range should be 0", 0, UpperBoundZero.getUpperBound(), 0);
    }
    
    /* 
     * This test covers the positive values for the upper bound with an arbitrary lower bound.
     */
    @Test
    public void testUpperBoundPositive() {
        assertEquals("The upper bound value of the range should be 20", 20.1, UpperBoundPositive.getUpperBound(), 0);
    }
    
    /* 
     * This test covers the negative values for the upper bound with an arbitrary lower bound.
     */
    @Test
    public void testUpperBoundNegative() {
        assertEquals("The upper bound value of the range should be -1", -1, UpperBoundNegative.getUpperBound(), 0);
    }
    
    /* 
     * This test covers char values for the upper bound with an arbitrary lower bound.
     */
    @Test
    public void testUpperBoundChar() {
        assertEquals("This test should fail because chars are not numbers, but passes because of char to int conversion", 'a', UpperBoundChar.getUpperBound(), 0);
    }
    
    /* 
     * This test covers the maximum value for the upper bound with an arbitrary lower bound.
     * It tests if the values around the boundary work properly using two unequal numbers and should fail in this case.
     */
    @Test
    public void testUpperBoundMax() {
        assertEquals("The upper bound value of the range should not be the max value for a double", Double.MAX_VALUE - 1000000000000000000000.0, UpperBoundMax.getUpperBound(), 0);
    }
    
    /* 
     * This test covers beyond the maximum value for the upper bound with an arbitrary lower bound.
     * It tests for values above the maximum value (infinity) and compares them to numbers below the maximum value.
     * The test is designed to fail since infinity does not equal a number below the max value of a double.
     */
    @Test
    public void testUpperBoundOverMax() {
    	assertEquals("The upper bound value of the range should be beyond the max value of a double"
    			, Double.MAX_VALUE - 10, UpperBoundOverMax.getUpperBound(), 0);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
