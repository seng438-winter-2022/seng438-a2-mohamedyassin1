package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

/*
 * This class is to test the getLowerBound() method using equivalence class testing and boundary value testing.
 */
public class RangeLowerBoundTest {
    private Range exampleRange;
    private Range LowerBoundZero;
    private Range LowerBoundPositive;
    private Range LowerBoundNegative;
    private Range LowerBoundChar;
    private Range LowerBoundMin;
    private Range LowerBoundUnderMin;
    // private Range RangeReversed;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
    	LowerBoundZero = new Range(0, 1);
    	LowerBoundPositive = new Range(20.1, 40.5);
    	LowerBoundNegative = new Range(-1, 100);
    	LowerBoundChar = new Range('b', 200);
    	LowerBoundMin = new Range(Double.MIN_VALUE, 1000000000);
        LowerBoundUnderMin = new Range (4.6E-324, 1000000000);
        
        // Reversing range does not work since the function requires lower < upper
        // RangeReversed = new Range (10, 1);
    }

    /* 
     * This test covers the lower bound value of 0 with an arbitrary upper bound.
     */
    @Test
    public void testLowerBoundZero() {
        assertEquals("The lower bound value of the range should be 0", 0, LowerBoundZero.getLowerBound(), 0);
    }
    
    /* 
     * This test covers the positive values for the lower bound with an arbitrary upper bound.
     */
    @Test
    public void testLowerBoundPositive() {
        assertEquals("The lower bound value of the range should be 20", 20.1, LowerBoundPositive.getLowerBound(), 0);
    }
    
    /* 
     * This test covers the negative values for the lower bound with an arbitrary upper bound.
     */
    @Test
    public void testLowerBoundNegative() {
        assertEquals("The lower bound value of the range should be -1", -1, LowerBoundNegative.getLowerBound(), 0);
    }
    
    /* 
     * This test covers char values for the lower bound with an arbitrary upper bound.
     */
    @Test
    public void testLowerBoundChar() {
        assertEquals("This test should fail because chars are not numbers, but passes because of char to int conversion", 'b', LowerBoundChar.getLowerBound(), 0);
    }
    
    /* 
     * This test covers the minimum value for the lower bound with an arbitrary upper bound.
     * It tests if the values around the boundary work properly using two unequal numbers and should fail in this case.
     */
    @Test
    public void testLowerBoundMin() {
        assertEquals("The lower bound value of the range should be the min value for a double", 5.0E-324, LowerBoundMin.getLowerBound(), 0);
    }
    
    /* 
     * This test covers below the minimum value for the lower bound with an arbitrary upper bound.
     * It tests for values below the minimum value (negative infinity) and compares them to numbers above the minimum value.
     * The test is designed to fail since negative infinity does not equal a number above the minimum value of a double.
     */
    @Test
    public void testLowerBoundUnderMin() {
    	assertEquals("The lower bound value of the range should be beyond the max value of a double"
    			, 4.8E-324, LowerBoundUnderMin.getLowerBound(), 0);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
