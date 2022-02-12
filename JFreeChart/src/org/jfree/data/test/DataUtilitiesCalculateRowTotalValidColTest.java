package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
/*
 * This class is testing the method calculateRowTotal(Values2D data, int row, int[] validCols)  in class DataUtilities
 * using equivalence class testing and boundary value testing.
 */
public class DataUtilitiesCalculateRowTotalValidColTest {
/*
 * This test tests a row with valid data (normal numbers), valid columns (they exist) and valid rows, which is the partition (0, limit -1)
 * We expect this to pass.
 */
	@Test
	 public void testRowTotalColumnValid() {
		    Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
		    mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(2));
		            one(values).getValue(0, 0);
		            will(returnValue(7.5));
		            one(values).getValue(0, 1);
		            will(returnValue(2.5));
		        }
		    });
		    int[] validCols = {0};
		    double result = DataUtilities.calculateRowTotal(values, 0, validCols);
		    assertEquals(7.5, result, 0);
	}
	/*
	 * This test tests a valid row and a valid column data, but the data is negative instead of positive this time.
	 * It tests the partition data(-limit+1, limit -1)
	 * We expect this to pass
	 */
	@Test
	public void testRowTotalColumnValidWithNegatives() {
		Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(-7.5));
	            one(values).getValue(0, 1);
	            will(returnValue(-2.5));
	        }
	    });
	    int[] validCols = {0};
	    double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	    assertEquals(-7.5, result, 0);
	}
	/*
	 * This test covers a valid row and data, but the column does not exist, so we expect 0 as nothing as added.
	 * It tests the partition Column index beyond limits
	 * We expect this test to pass.
	 */
	@Test
	public void testRowTotalInvalidColumn() {
		Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(-7.5));
	            one(values).getValue(0, 1);
	            will(returnValue(-2.5));
	        }
	    });
	    int[] validCols = {4};
	    double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	    assertEquals(0, result, 0);
	}
	/*
	 * This test covers a valid data and column, but an invalid row, as we are inputting a negative row number, we expect this test to
	 * still pass because we tried catching an exception, but it failed, so we assume this is a bug.
	 * We are covering the partition Row index out of bounds.
	 */
	@Test
	public void testValidRowTotalWithValidColumnButNegativeRowIndex() {
		Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(-7.5));
	            one(values).getValue(0, 1);
	            will(returnValue(-2.5));
	        }
	    });
	    int[] validCols = {0,1};
	    try {
	    	double result = DataUtilities.calculateRowTotal(values, -0, validCols);
	    	fail("Cannot have negative row indexes, this should not work");
	    }
	    catch (Exception exc) { //assuming an exception would be thrown here
	    	assertNotNull(exc);
	    }
	}
}
