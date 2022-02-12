package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class DataUtilitiesCalculateRowTotalTest {
	/*
	 * This test case is for method calculateRowTotal in class DataUtilities
	 * It tests the partition where all the numbers in the row are negative.
	 */
	 @Test
	 public void testAllNegativeRow() {
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
		    double result = DataUtilities.calculateRowTotal(values, 0);
		    assertEquals(-10.0, result, 0);
	 }
	 /*
	  * This test case is for the method calculateRowTotal() in class DataUtilities
	  * It tests the partition where the row has an overall negative value
	  */
	 @Test
	 public void testOverallNegativeRow() {
		    Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
		    mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(2));
		            one(values).getValue(0, 0);
		            will(returnValue(-7.5));
		            one(values).getValue(0, 1);
		            will(returnValue(4));
		        }
		    });
		    double result = DataUtilities.calculateRowTotal(values, 0);
		    assertEquals(-3.5, result, 0);
	 }
	 /*
	  * This test case is for the method calculateRowTotal in class DataUtilities
	  * It tests the partition where the row has an overall positive value
	  */
	 @Test
	 public void testMostlyPositiveRow() {
		    Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
		    mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(2));
		            one(values).getValue(0, 0);
		            will(returnValue(7.5));
		            one(values).getValue(0, 1);
		            will(returnValue(-2.5));
		        }
		    });
		    double result = DataUtilities.calculateRowTotal(values, 0);
		    assertEquals(5.0, result, 0);
	 }
	 /*
	  * This test case is for the method calculateRowTotal in class DataUtilities
	  * It tests the partition where the row is a negative version of a row that already exists
	  * We expect this test to pass, since we throw an exception, but it failed, so we assume this is a bug.
	  */
	 @Test
	 public void testNegativeIndexRow() {
		    Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
		    mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(2));
		            one(values).getValue(0, 0);
		            will(returnValue(7.5));
		            one(values).getValue(0, 1);
		            will(returnValue(-2.5));
		        }
		    });
		    try {
		    	double result = DataUtilities.calculateRowTotal(values, -0);
		    	fail("Should throw an exception here since negative rows don't exist");
		    }
		    catch (Exception e){
		    	assertNotNull(e);
		    }
	 }
	 /*
	  * This test case is for the method calculateRowTotal in class DataUtilities
	  * It tests the partition where the first index of the row is a null value
	  */
	 @Test
	 public void testFirstIndexNull() {
		    Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
		    mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(2));
		            one(values).getValue(0, 0);
		            will(returnValue(null));
		            one(values).getValue(0, 1);
		            will(returnValue(2.5));
		        }
		    });
		    double result = DataUtilities.calculateRowTotal(values, 0);
		    assertEquals(2.5, result, 0);
	 }
	 /*
	  * This test case is for the method calculateRowTotal in class DataUtilities
	  * It tests the partition where the last index of the row is a null value
	  */
	 @Test
	 public void testLastIndexNull() {
		    Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
		    mockingContext.checking(new Expectations() {
		        {
		            one(values).getColumnCount();
		            will(returnValue(2));
		            one(values).getValue(0, 0);
		            will(returnValue(50.45));
		            one(values).getValue(0, 1);
		            will(returnValue(null));
		        }
		    });
		    double result = DataUtilities.calculateRowTotal(values, 0);
		    assertEquals(50.45, result, 0);
	 }
}

