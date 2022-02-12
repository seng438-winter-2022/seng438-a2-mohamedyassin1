package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

/*
 * This class is testing the method getCumulativePercentage(KeyedValues values) in class Data Utilities 
 * We tested it with boundary value and equivalence case testing.
 */
public class DataUtilitiesGetCumulativePercentageTest {
	/*
	 * Here we test a valid ordered list
	 * Meaning all the numbers are valid inputs in ascending order. This tests the partition data > 0 ,KeyValues[0] < KeyValues[1] < KeyValues[2] < …
	 * We expect this test to pass
	 */
	@Test
    public void testValidOrderedList() throws Exception {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            atMost(100).of(values).getItemCount();
            will(returnValue(3));
            
            atMost(100).of(values).getValue(0);
            will(returnValue(10));
            one(values).getKey(0);
            will(returnValue(0));
            
            atMost(100).of(values).getValue(1);
            will(returnValue(20));
            one(values).getKey(1);
            will(returnValue(1));
            
            atMost(10).of(values).getValue(2);
            will(returnValue(20));
            one(values).getKey(2);
            will(returnValue(2));
            }
        });  
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertEquals(0.2, result.getValue(0));
        assertEquals(0.6, result.getValue(1));
        assertEquals(1.0, result.getValue(2));
    }
	/*
	 * This test is testing a valid unordered list, same as the previous test but the data
	 * is not in ascending order.
	 * We expect this test to pass.
	 */
	@Test
	public void testValidUnOrderedList() throws Exception {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            atMost(100).of(values).getItemCount();
            will(returnValue(3));
            
            atMost(100).of(values).getValue(0);
            will(returnValue(70));
            one(values).getKey(0);
            will(returnValue(0));
                       
            atMost(10).of(values).getValue(2);
            will(returnValue(20));
            one(values).getKey(2);
            will(returnValue(2));
            
            atMost(100).of(values).getValue(1);
            will(returnValue(10));
            one(values).getKey(1);
            will(returnValue(1));
            }
        });  
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertEquals(0.7, result.getValue(0));
        assertEquals(0.8, result.getValue(1));
        assertEquals(1.0, result.getValue(2));
	}
	/*
	 * This test is testing an Invalid List in which we put in chars instead of data,
	 * so we throw an exception to detect the error, if this test passes, there is no bug.
	 * partition covered: data = char
	 */
	@Test
	public void testInvalidList() throws Exception {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            atMost(100).of(values).getItemCount();
            will(returnValue(3));
            
            atMost(100).of(values).getValue(0);
            will(returnValue('a'));
            one(values).getKey(0);
            will(returnValue(0));
                                   
            atMost(100).of(values).getValue(1);
            will(returnValue('z'));
            one(values).getKey(1);
            will(returnValue(1));
            
            atMost(10).of(values).getValue(2);
            will(returnValue('h'));
            one(values).getKey(2);
            will(returnValue(2));
            }
        });  
        try {
        	 KeyedValues result = DataUtilities.getCumulativePercentages(values);
        	 fail("Should not be able to get percentages from characters");
        }
        catch(Exception exc){
        	assertNotNull(exc);
        }
	}
	/*
	 * This test is for the partition data < 0, we expect a result of 0.7 if the
	 * numbers are correctly calculated, but instead we got 1.16, this should be a bug, an acceptable answer
	 * would have been a null as well, as negatives should not be supported in percentages but since we got 
	 * a real answer that did not match what we expected, this is a bug.
	 */
	@Test
	public void testValidListWithNegativeNumbers() throws Exception {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            atMost(100).of(values).getItemCount();
            will(returnValue(3));
            
            atMost(100).of(values).getValue(0);
            will(returnValue(70));
            one(values).getKey(0);
            will(returnValue(0));
                       
            atMost(10).of(values).getValue(2);
            will(returnValue(-20));
            one(values).getKey(2);
            will(returnValue(2));
            
            atMost(100).of(values).getValue(1);
            will(returnValue(10));
            one(values).getKey(1);
            will(returnValue(1));
            }
        });  
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertEquals(0.7, result.getValue(0));
        assertEquals(0.5, result.getValue(1));
        assertEquals(0.6, result.getValue(2));
	}
	/*
	 * This test is testing the partition data = null
	 * We expect this test to pass since the method states to not put in null values
	 * but an exception is never thrown so this is a bug
	 */
	@Test
	public void testNullArgumentList() throws Exception {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            atMost(100).of(values).getItemCount();
            will(returnValue(3));
            
            atMost(100).of(values).getValue(0);
            will(returnValue(40));
            one(values).getKey(0);
            will(returnValue(0));
                       
            atMost(10).of(values).getValue(1);
            will(returnValue(10));
            one(values).getKey(1);
            will(returnValue(1));
            
            atMost(100).of(values).getValue(2);
            will(returnValue(null));
            one(values).getKey(2);
            will(returnValue(2));
            }
        });  
        	KeyedValues result = DataUtilities.getCumulativePercentages(values);
        	assertEquals(0.8, result.getValue(0));
        	assertEquals(1.0,result.getValue(1));
        	try {
        		assertEquals(null, result.getValue(2));
        		fail("There should not be nulls on the data table, this test failed since nulls are accounted");
        	}
        	catch (Exception exc) { //Not sure what exception is thrown here, but there should be one since table cant have nulls.
        		assertNotNull(exc);
        	}
	}
	/*
	 * This test is testing the partition key > max
	 * We expect this test to pass
	 */
	@Test
	public void testKeyOutsideBounds() throws Exception {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            atMost(100).of(values).getItemCount();
            will(returnValue(3));
            
            atMost(100).of(values).getValue(0);
            will(returnValue(30));
            one(values).getKey(0);
            will(returnValue(0));
                       
            atMost(10).of(values).getValue(1);
            will(returnValue(10));
            one(values).getKey(1);
            will(returnValue(1));
            
            atMost(100).of(values).getValue(2);
            will(returnValue(10));
            one(values).getKey(2);
            will(returnValue(2));
            }
        });  
        	KeyedValues result = DataUtilities.getCumulativePercentages(values);
        	assertEquals(0.6, result.getValue(0));
        	assertEquals(0.8,result.getValue(1));
        	try {
        		result.getValue(5);
        		fail("This index is out of bounds, should not work");
        	}
        	catch (Exception exc) {
        		assertNotNull(exc);
        	}
	}
}
