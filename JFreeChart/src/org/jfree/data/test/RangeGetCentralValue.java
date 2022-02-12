package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range;
import org.junit.*;

public class RangeGetCentralValue  {

	
	private Range exampleRange;
	    @BeforeClass public static void setUpBeforeClass() throws Exception {
	    } 


	    @Before
	    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
	    }

	 
	    
	    
	    @Test
	    public void testCentralValuesPositive() {
	    	//This test is testing partition of (positive, positive)
	    	
	    	exampleRange = new Range(2,8);
	    	System.out.println( exampleRange.getCentralValue());
	        assertEquals("The central value of 2 and 8 should be 5",
	        5, exampleRange.getCentralValue(), .000000001d);
	    }
	    @Test
	    public void testCentralValuesNegative() {
	    	//This test is testing partition of (negative, negative)
	    	exampleRange = new Range(-8,-2);
	    	System.out.println( exampleRange.getCentralValue());
	        assertEquals("The central value of -2 and -8 should be -5",
	        -5, exampleRange.getCentralValue(), .000000001d);
	    }
	    @Test
	    public void testCentralValuesZeros() {
	    	//This test is testing partition of (0, 0)
	    	exampleRange = new Range(0,0);
	    	System.out.println( exampleRange.getCentralValue());
	        assertEquals("The central value of 0 and 0 should be 0",
	        0, exampleRange.getCentralValue(), .000000001d);
	    }
	    @Test
	    public void testCentralValuesEvens() {
	    	//This test is testing partition of (even, even)
	    	exampleRange = new Range(-6,-4);
	    	System.out.println( exampleRange.getCentralValue());
	        assertEquals("The central value of -4 and -6 should be -5",
	        -5, exampleRange.getCentralValue(), 0);
	    }
	    @Test
	    public void testCentralValuesSpreadPositive() {
	    	//This test is testing partition of (positive even , positive odd)
	    	exampleRange = new Range(2,7);
	    	System.out.println( exampleRange.getCentralValue());
	        assertEquals("The central value of 2 and 7 should be 4.5",
	        4.5, exampleRange.getCentralValue(), .000000001d);
	    }
	    @Test
	    public void testCentralValuesSpreadNegative() {
	    	//This test is testing partition of (negative odd, negative even)
	    	exampleRange = new Range(-7,-2);
	    	System.out.println( exampleRange.getCentralValue());
	        assertEquals("The central value of -2 and -7 should be -4.5",
	        -4.5, exampleRange.getCentralValue(), .000000001d);
	    }
	    @Test
	    public void testCentralValuesSpread() {
	    	//This test is testing partition of (negative even, positive odd)
	    	exampleRange = new Range(-2,7);
	    	System.out.println( exampleRange.getCentralValue());
	        assertEquals("The central value of -2 and 7 should be 2.5",
	        2.5, exampleRange.getCentralValue(), .000000001d);
	    }	
	    @After
	    public void tearDown() throws Exception {
	    }

	    @AfterClass
	    public static void tearDownAfterClass() throws Exception {
	    }

}
