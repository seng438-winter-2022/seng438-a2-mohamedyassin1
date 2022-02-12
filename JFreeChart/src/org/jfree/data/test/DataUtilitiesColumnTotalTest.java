package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.Test;
import org.jmock.Expectations;
import org.jmock.Mockery;


/*
 * This class tests for the calculateColumnTotal(Values2D data, int column) method using equivalence class testing and boundary value testing.
 */
public class DataUtilitiesColumnTotalTest {

	/*
	 * This test covers a column with data values of 0.
	 */
	@Test
	public void testColumnDataIsZero() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(0));
	            one(values).getValue(1, 0);
	            will(returnValue(0));
	        }
	    });
	    // exercise	
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    // verify
	    assertEquals("The sum of the columns should be 0", 0, result, 0);
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers a column with only positive data values. 
	 */
	@Test
	public void testColumnPositiveData() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	        }
	    });
	    // exercise	
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    // verify
	    assertEquals("The sum of the columns should be 10", 10.0, result, 0);
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers a column with only negative data values. 
	 */
	@Test
	public void testColumnNegativeData() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(-7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(-2.5));
	        }
	    });
	    // exercise	
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    // verify
	    assertEquals("The sum of the columns should be -10", -10.0, result, 0);
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers a column with positive, negative and data values of 0. 
	 */
	@Test
	public void testColumnMixedData() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(4));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	            one(values).getValue(2, 0);
	            will(returnValue(-2.5));
	            one(values).getValue(3, 0);
	            will(returnValue(0));
	        }
	    });
	    // exercise	
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    // verify
	    assertEquals("The sum of the columns should be 7.5", 7.5, result, 0);
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers a column index value at the boundary.
	 */
	@Test
	public void testColumnBoundary() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	            one(values).getValue(2, 0);
	            will(returnValue(-2.5));
	            one(values).getValue(0, 1);
	            will(returnValue(20));
	            one(values).getValue(1, 1);
	            will(returnValue(30.4));
	            one(values).getValue(2, 1);
	            will(returnValue(-2.5));
	        }
	    });
	    // exercise	
	    double result = DataUtilities.calculateColumnTotal(values, 1);
	    // verify
	    assertEquals("The sum of the columns should be 47.9", 47.9, result, 0);
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers a column index value outside the boundary.
	 * It is designed to fail since there are no columns outside the boundary.
	 */
	@Test
	public void testColumnOutsideBoundary() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	            one(values).getValue(2, 0);
	            will(returnValue(-2.5));
	            one(values).getValue(0, 1);
	            will(returnValue(20));
	            one(values).getValue(1, 1);
	            will(returnValue(30.4));
	            one(values).getValue(2, 1);
	            will(returnValue(-2.5));
	        }
	    });
	    // exercise	
	    try {
	    	double result = DataUtilities.calculateColumnTotal(values, 2);
		    // verify
		    assertEquals("The sum of the columns should give an error", 0, result, 0);
	    } catch (Exception e) {
	    	fail("Fail");
	    }
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers columns with values of null data. It should ignore the null value 
	 * since Values2D uses Numbers objects instead of primitive data types, and thus should pass. 
	 */
	@Test
	public void testColumnTotalNullData() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));
	            one(values).getValue(0, 0);
	            will(returnValue(null));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	            one(values).getValue(2, 0);
	            will(returnValue(-2.5));
	            one(values).getValue(0, 1);
	            will(returnValue(20));
	            one(values).getValue(1, 1);
	            will(returnValue(30.4));
	            one(values).getValue(2, 1);
	            will(returnValue(-2.5));
	        }
	    });
	    // exercise	
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    // verify
	    assertEquals("The sum of the columns should give an error", 0, result, 0);
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers negative column index values. 
	 */
	@Test
	public void testNegativeColumnIndex() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	            one(values).getValue(2, 0);
	            
	            will(returnValue(-2.5));
	            one(values).getValue(0, 1);
	            will(returnValue(20));
	            one(values).getValue(1, 1);
	            will(returnValue(30.4));
	            one(values).getValue(2, 1);
	            will(returnValue(-2.5));
	            
	            one(values).getValue(0, -1);
	            will(returnValue(20));
	            one(values).getValue(1, -1);
	            will(returnValue(30.4));
	            one(values).getValue(2, -1);
	            will(returnValue(-2.5));
	            
	        }
	    });
	    // exercise	
	    double result = DataUtilities.calculateColumnTotal(values, -1);
	    // verify
	    assertEquals("The sum of the columns should be 47.9", 47.9, result, 0);
	    // tear-down: NONE in this test method
	}
	
	/*
	 * This test covers columns with illegal(char) data values.
	 * This test should fail since the table should only accept Numbers.
	 */
	@Test
	public void testColumnTotalIllegal() throws Exception {
	    // setup
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));
	            one(values).getValue(0, 0);
	            will(returnValue('a'));
	            one(values).getValue(1, 0);
	            will(returnValue('b'));
	            one(values).getValue(2, 0);
	            will(returnValue('c'));
	        }
	    });
	    // exercise	
	    try {
	    	double result = DataUtilities.calculateColumnTotal(values, 0);
	    	assertEquals("The sum of the columns should not work", 'a' + 'b' + 'c', result, 0);
	    } catch(Exception e) {
	    	fail("Exception is thrown and there was a failure.");
	    }
	    // verify
	    //assertEquals("The sum of the columns should not work", 'a' + 'b' + 'c', result, 0);
	    // tear-down: NONE in this test method
	}

}
