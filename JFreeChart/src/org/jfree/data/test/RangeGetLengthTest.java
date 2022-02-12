package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; import org.junit.*;

public class RangeGetLengthTest {
    private Range twoNegativeBounds; //For testNegativeBounds() method
    private Range positiveUpperBoundNegativeLowerBound; //for testPositiveUpperBoundNegativeLowerBound() method
    private Range bothBoundsPositive; //for testbothBoundsPositive() method
    private Range bothBoundsHaveDecimals;//for testbothBoundsHaveDecimals() method
    private Range bothBoundsZero; // for testBothBoundsZero() method
    @BeforeClass 
    public static void setUpBeforeClass() throws Exception {
    }
    @Before
    public void setUp() throws Exception { 
    	//first element in Range is lower bound, second element is upper bound according to Constructor Details provided.
    	//lowerBound must be <= upper bound
    	twoNegativeBounds = new Range(-9,-5); 
    	positiveUpperBoundNegativeLowerBound = new Range(-5,8); 
    	bothBoundsPositive = new Range(10,20);
    	bothBoundsHaveDecimals = new Range(10.6,20.3);
    	bothBoundsZero = new Range(0,0);
    }

    /*
     * This test is for method getLength() in class Range
     * This test tests the partition in which the Range object has both bounds as negative numbers.
     */
    @Test
    public void testNegativeBounds() {
    	assertEquals("The length should be 4", 4, twoNegativeBounds.getLength(), 0);
    }
    /*
     * This test is for method getLength() in class Range
     * This test tests the partition in which the Range object has a positive upper bound and a negative lower bound
     */
    @Test
    public void testPositiveUpperBoundNegativeLowerBound() {
    	assertEquals("The length should be 13", 13, twoNegativeBounds.getLength(), 0);
    }
    /*
     * This test is for method getLength() in class Range
     * This test tests the partition in which the Range object has both bounds as positive numbers.
     */
    @Test
    public void testbothBoundsPositive() {
    	assertEquals("The length should be 10", 10, bothBoundsPositive.getLength(), 0);
    }
    /*
     * This test is for method getLength() in class Range
     * This test tests the partition in which the Range object has both bounds with decimal numbers.
     */
    @Test
    public void testbothBoundsHaveDecimals() {
    	assertEquals("The length should be 9.7", 9.7, bothBoundsHaveDecimals.getLength(), 0.1d);
    }
    /* 
     * This test is for method getLength() in class Range
     * This test tests the partition in which the Range object has both bounds as zero.
     */
    @Test
    public void testBothBoundsZero() {
    	assertEquals("The length should be 0", 0, bothBoundsZero.getLength(), 0);
    }
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}