package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
/*
 * This class tests the CalculateColumnTotal(Values2D data, int column, int[] validRows) method in class DataUtilities using
 * equivalence class testing and boundary testing.
 */
public class DataUtilitiesCalculateColumnTotalWithRows {
	
	@Test
    public void testColumnValid() {
		//This test is testing partition of (0, limit -1)
		
		
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
                one(values).getValue(2, 0);
                will(returnValue(5.5));
            }
        });
        int[] validRows = new int[]{1,2};
        double result = DataUtilities.calculateColumnTotal(values, 0,validRows);
        // verify
        assertEquals(result, 8.0, .000000001d);
        // tear-down: NONE in this test method
    }
	

	@Test
    public void testInvalidColumn() throws Exception {
		//This test is testing partition of Column index beyond limits
		
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
                one(values).getValue(2, 0);
                will(returnValue(5.5));
                
                
            }
        });
        int[] validRows = new int[]{1,2};
     
        // verify
        
            try {
         	   double result = DataUtilities.calculateColumnTotal(values, 1,validRows);
             fail("Expected exception was not thrown");
         } catch (Exception e) {
             assertNotNull(e);
         }
        	
           
        
        // tear-down: NONE in this test method
    }	
	
	
	@Test
    public void testInvalidRow() throws Exception {
		//This test is testing partition of Row index beyond limits
		
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
                one(values).getValue(2, 0);
                will(returnValue(5.5));
            }
        });
        int[] validRows = new int[]{4};
     
        // verify
        try {
        	   double result = DataUtilities.calculateColumnTotal(values, 0,validRows);
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertNotNull(e);
        }
        // tear-down: NONE in this test method
        
        
    }	
	
	@Test
    public void testColumnValidNegative() {
		//This test is testing partition of (-limit+1, limit -1)	
		
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(4));
                one(values).getValue(0, 0);
                will(returnValue(-7.5));
                one(values).getValue(1, 0);
                will(returnValue(-2.5));
                one(values).getValue(2, 0);
                will(returnValue(5.5));
                one(values).getValue(3, 0);
                will(returnValue(7));
            }
        });
        int[] validRows = new int[]{0,1,3};
        double result = DataUtilities.calculateColumnTotal(values, 0,validRows);
        
        // verify
        assertEquals("Result should be -3 but is "+result,result, -3.0, .000000001d);
        // tear-down: NONE in this test method
    }	
	
	
}
