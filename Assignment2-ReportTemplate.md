**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:       | 32  |
|-----------------|---|
| Student Names:  |   |
|                 |  Benson Li      |
|                 |  Henrique Andras |
|                 |  Kevin Araujo  |
|                 |   Mohamed Yassin  |

# 1 Introduction

This lab introduces the fundamentals of automated testing based on the requirements for each method. The tools used are the Eclipse IDE with Java and the JUnit framework which is a widely used testing tool for Java. Throughout the lab, we will learn how to set up JUnit tests, develop test suites based on requirements specified in the Javadoc, working with mock objects to test code, and to put the system under test by executing code that we have developed. 

# 2 Detailed description of unit test strategy

To do this testing in a detailed and efficient manner, we will be following this approach:



1. Read the test class documentation for one method at a time and take time to understand how the method works.
2. Create test cases while keeping the following things in mind: Give long, descriptive names to test case methods. Give descriptive names for the expected/actual values to separate between them. In the assertions, the expected value should be at the left. Don’t have too many asserts, try to find a few asserts that are good rather than 100 different asserts. Tests should generally avoid too much logic. Since we are given the requirements only, we will use black-box techniques such as the equivalence-class partitioning technique and the boundary value analysis technique to test the classes, we will pick the best technique for each method. To make our code clean, we will organize our test cases into test suites.

## **<span style="text-decoration:underline;">Class Range:</span>**



* **getLength()**: We will be using partitioning to split up the input set into categories that behave the same way. For example, in theory, two negative values between -50 and -10 should behave the same way, and so forth. To test how this method behaves with various lower and upper bound values, we will have these combinations of the upper and lower bound values. The goal here is to avoid repetition and find as many faults as possible in as few cases as possible. We assume the lower bound is the one being subtracted to make the numbers stay positive. We will test based on the following partitions.
    * Two negative bounds 
    * Positive upper bound, negative lower bound
    * Negative upper bound, positive lower bound
    * Upper bound NULL 
    * Lower bound NULL
    * Two NULL values
* **getUpperBound()**: We will use different range objects and test equivalence classes and test the upper bound value as shown:
    * Zero
    * Positive value
    * Negative value
    * NULL
    * Illegal value
    * Max value of a double
    * Min value of a double
    * Range is reversed: Range(upper, lower)
* **getLowerBound()**: We will use different range objects and test equivalence classes and test the lower bound value as shown:
    * Zero
    * Positive value
    * Negative value
    * Illegal value(not a number)
    * Max value
    * No values
* **getCentralValue():** This method has the goal of returning the central value of a range object. In theory, it should get the lower and upper bounds of the current range object and based on that calculate which number is exactly in the middle between them. The expected outcome of if the two numbers are both even or both odd(meaning there exists a whole number as a middle point), is to find the whole number that signifies the middle point, the expected outcome of if the numbers are one odd and one even(meaning there does not exist a whole number as a middle point) is to take the average of the two middle numbers. Because of that, we make the even and odd differentiation.  We will use different range objects and test equivalence classes and test the central value as shown
    * 2  valid even positive numbers
    * 2  valid even negative numbers
    * 2 zeros
    * One even positive and one even negative valid numbers
    * One even and one odd positive numbers
    * One even and one odd negative numbers
    * One odd positive and one even negative valid number
* **contains(double value)**: We will examine a variety of values and test the contains method as shown:
    * Positive number
    * Negative number
    * Very large number
    * Very small number
    * Value within the range
    * Value outside the range
    * Value on the range boundaries

## **<span style="text-decoration:underline;">Class DataUtilities</span>**



* calculateColumnTotal(Values2D data, int column): We will test this method by testing equivalence classes in the data and testing doing boundary value analysis on the column values
    * Valid data
    * Column at boundary
    * Column outside boundary
    * Null data
    * Illegal data
    * Negative column int 
* **calculateColumnTotal(Values2D data, int column, int[] validRows)**: We will test this method by testing a couple of selected equivalence classes which are listed below:
    * Valid data, valid column, valid rows
    * Valid data including negatives,valid column, valid rows
    * Valid data, invalid column, valid rows
    * Valid data, valid column, invalid rows
* **calculateRowTotal(Values2D data, int row)**: Based on the requirements, this method will add every value in a row and return a total number. For this method, we will be using a Mock Values2D object and rows of different inputs. 
    * A row with all negative values.
    * A row with an overall negative result.
    * A row with an overall positive result.
    * A row with a NULL as the first index.
    * A row with a NULL as the last index.
    * A row that does not exist in the data table.
* **calculateRowTotal(Values2D data, int row, int[] validCols)**:
    * An empty columns array.
    * A full columns array.
    * Half-full columns array
    * A columns array with negative values
    * A columns array with null values
    * A column with duplicate values
* **getCumulativePercentages(KeyedValues data)**: We will test this method by manipulating KeyValues using mockery as follows: 
    * Valid ordered list
    * Valid unordered list
    * Invalid list
    * Valid list with negative numbers
    * Null arguments



# 3 Test cases developed

## **<span style="text-decoration:underline;">Class Range</span>**

**getLength() : first element in Range constructor is lower bound, second element in Range constructor is upper bound.**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Changes</strong>
   </td>
   <td><strong>Expected Result</strong>
   </td>
   <td><strong>Partition covered</strong>
   </td>
  </tr>
  <tr>
   <td>testNegativeBounds()
   </td>
   <td>Upper and Lower bound are negative
   </td>
   <td>The absolute value of the difference between both bounds.
   </td>
   <td>(- , -)
   </td>
  </tr>
  <tr>
   <td>posUpNegDownLength()
   </td>
   <td>Upper bound positive, lower bound negative
   </td>
   <td>Absolute value of difference between upper and lower bound.
   </td>
   <td>(-,+)
   </td>
  </tr>
  <tr>
   <td>testBothPositive()
   </td>
   <td>Both bounds positive
   </td>
   <td>Difference between both bounds
   </td>
   <td>(+,+)
   </td>
  </tr>
  <tr>
   <td>testWithDecimals()
   </td>
   <td>Bounds have decimals
   </td>
   <td>Difference between both bounds, with decimals
   </td>
   <td>Both bounds have decimals
   </td>
  </tr>
  <tr>
   <td>testBothBoundsZero()
   </td>
   <td>Both bounds are 0
   </td>
   <td>Length is 0
   </td>
   <td>(0,0)
   </td>
  </tr>
</table>


**getUpperBound()**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Arguments </strong>
   </td>
   <td><strong>Partition covered</strong>
   </td>
  </tr>
  <tr>
   <td>testUpperBoundZero()
   </td>
   <td>Upper bound is 0
   </td>
   <td>upper bound = 0
   </td>
  </tr>
  <tr>
   <td>testUpperBoundPositive()
   </td>
   <td>Upper bound is a positive value
   </td>
   <td>0 &lt; upper bound &lt; limit
   </td>
  </tr>
  <tr>
   <td>testUpperBoundNegative()
   </td>
   <td>Upper bound is a negative value
   </td>
   <td>-limit &lt; upper bound &lt; 0
   </td>
  </tr>
  <tr>
   <td>testUpperBoundChar()
   </td>
   <td>Upper bound is a char
   </td>
   <td>upper bound = char
   </td>
  </tr>
  <tr>
   <td>testUpperBoundMax()
   </td>
   <td>Upper bound is the max value for a double 
   </td>
   <td>upper bound = limit
   </td>
  </tr>
  <tr>
   <td>testUpperBoundOverMax()
   </td>
   <td>Upper bound is larger than the max value for a double
   </td>
   <td>upper bound > limit
   </td>
  </tr>
  <tr>
   <td>testRangeReversed()
   </td>
   <td>Upper bound value is switched with the lower bound value
   </td>
   <td>upper &lt; lower
   </td>
  </tr>
</table>


**getLowerBound()**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Arguments </strong>
   </td>
   <td><strong>Partition covered</strong>
   </td>
  </tr>
  <tr>
   <td>testLowerBoundZero()
   </td>
   <td>Lower bound is 0
   </td>
   <td>Lower bound = 0
   </td>
  </tr>
  <tr>
   <td>testLowerBoundPositive()
   </td>
   <td>Lower bound is a positive value
   </td>
   <td>0 &lt; Lower bound &lt; limit
   </td>
  </tr>
  <tr>
   <td>testLowerBoundNegative()
   </td>
   <td>Lower bound is a negative value
   </td>
   <td>-limit &lt; Lower bound &lt; 0
   </td>
  </tr>
  <tr>
   <td>testLowerBoundChar()
   </td>
   <td>Lower bound is a char
   </td>
   <td>Lower bound = char
   </td>
  </tr>
  <tr>
   <td>testLowerBoundMax()
   </td>
   <td>Lower bound is the max value for a double 
   </td>
   <td>Lower bound = limit
   </td>
  </tr>
  <tr>
   <td>testLowerBoundOverMax()
   </td>
   <td>Lower bound is larger than the max value for a double
   </td>
   <td>Lower bound > limit
   </td>
  </tr>
</table>


**getCentralValue()**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Arguments </strong>
   </td>
   <td><strong>Partition covered</strong>
   </td>
  </tr>
  <tr>
   <td>testCentralValuesPositive()
   </td>
   <td>Upper and Lower bound are 2 valid even positive numbersnegative
   </td>
   <td>(2,8)
   </td>
  </tr>
  <tr>
   <td>testCentralValuesNegative()
   </td>
   <td>Upper and Lower boundare 2  valid even negative numbers
   </td>
   <td>(-2,-8)
   </td>
  </tr>
  <tr>
   <td>testCentralValuesZeros()
   </td>
   <td>Upper and Lower bound are 2 zeros
   </td>
   <td>(0,0)
   </td>
  </tr>
  <tr>
   <td>testCentralValuesEvens()
   </td>
   <td>Upper and Lower bound are one even positive and one even negative valid numbers
   </td>
   <td>(-4,6)
   </td>
  </tr>
  <tr>
   <td>testCentralValuesSpreadPositive()
   </td>
   <td>Upper and Lower bound are one even and one odd positive numbers
   </td>
   <td>(2,7)
   </td>
  </tr>
  <tr>
   <td>testCentralValuesSpreadNegative()
   </td>
   <td>Upper and Lower bound are one even and one odd negative numbers
   </td>
   <td>(-2,-7)
   </td>
  </tr>
  <tr>
   <td>testCentralValuesSpread()
   </td>
   <td>Upper and Lower bound are one odd positive and one even negative valid number
   </td>
   <td>(-2,7)
   </td>
  </tr>
</table>


**contains(double value)**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Arguments</strong>
   </td>
   <td><strong>Expected Result</strong>
   </td>
   <td><strong>Partition Covered</strong>
   </td>
  </tr>
  <tr>
   <td>testContainsPositiveNumber()
   </td>
   <td>Value is a valid positive value within range
   </td>
   <td>true
   </td>
   <td>Value > 0
   </td>
  </tr>
  <tr>
   <td>testContainsNegativeNumber()
   </td>
   <td>Value is a negative value within range
   </td>
   <td>true
   </td>
   <td>Value &lt; 0
   </td>
  </tr>
  <tr>
   <td>testContainsPositiveValueOutsideTheRange()
   </td>
   <td>Value is positive and outside of the range
   </td>
   <td>false
   </td>
   <td>Value > 0
<p>
Value > range
   </td>
  </tr>
  <tr>
   <td>testContainsNegativeValueOutsideTheRange()
   </td>
   <td>Value is negative and outside of the range
   </td>
   <td>false
   </td>
   <td>Value &lt; 0
<p>
Value > range
   </td>
  </tr>
  <tr>
   <td>testContainsValueUpperBound()
   </td>
   <td>Value is boundary value on upper range
   </td>
   <td>true
   </td>
   <td>Value = upper range
   </td>
  </tr>
  <tr>
   <td>testContainsValueLowerBound()
   </td>
   <td>Value is a boundary value on lower range
   </td>
   <td>true
   </td>
   <td>Value = lower range
   </td>
  </tr>
</table>




## **<span style="text-decoration:underline;">Class DataUtilities</span>**

**calculateColumnTotal(Values2D data, int column)**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Arguments</strong>
   </td>
   <td><strong>Expected Result</strong>
   </td>
   <td><strong>Partition Covered</strong>
   </td>
  </tr>
  <tr>
   <td>testColumnDataIsZero()
   </td>
   <td>Column has all zero values
   </td>
   <td>The sum = 0
   </td>
   <td>data = 0
   </td>
  </tr>
  <tr>
   <td>testColumnPositiveData()
   </td>
   <td>Column has all positive values
   </td>
   <td>The values should be summed up regardless of positive or negative data
   </td>
   <td>0 &lt; data &lt; limit
<p>
-limit &lt; column &lt; limit
   </td>
  </tr>
  <tr>
   <td>testColumnNegativeData()
   </td>
   <td>Column has all negative values
   </td>
   <td>The values should be summed up regardless of positive or negative data
   </td>
   <td>-limit &lt; data &lt; 0
<p>
-limit &lt; column &lt; limit
   </td>
  </tr>
  <tr>
   <td>testColumnMixedData
   </td>
   <td>Column has a mix of negative and positive values
   </td>
   <td>The values should be summed up regardless of positive or negative data
   </td>
   <td>-limit &lt; data &lt; limit
   </td>
  </tr>
  <tr>
   <td>testColumnBoundary()
   </td>
   <td>Column index is at the boundary of the 2D table
   </td>
   <td>The values should be summed up regardless of positive or negative data
   </td>
   <td>Column = limit
   </td>
  </tr>
  <tr>
   <td>testColumnOutsideBoundary()
   </td>
   <td>Column index is outside the boundary of the 2D table
   </td>
   <td>An error should occur
   </td>
   <td>Column > limit
   </td>
  </tr>
  <tr>
   <td>testColumnTotalNullData()
   </td>
   <td>Column has null data values
   </td>
   <td>It should ignore the null value since it uses objects instead of primitive data types.
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>testNegativeColumnIndex()
   </td>
   <td>A negative index for a column
   </td>
   <td>The data values should be summed up regardless of column index.
   </td>
   <td>Column &lt; 0
   </td>
  </tr>
  <tr>
   <td>testColumnTotalCIllegal()
   </td>
   <td>A column with illegal data values like letters and special characters
   </td>
   <td>Since data values are not numbers, it should output an error.
   </td>
   <td>Data = char
   </td>
  </tr>
</table>


**calculateRowTotal(Values2D data, int row)**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Changes</strong>
   </td>
   <td><strong>Expected Result</strong>
   </td>
   <td><strong>Partition Covered</strong>
   </td>
  </tr>
  <tr>
   <td>testAllNegativeRow()
   </td>
   <td>A row with all negative numbers
   </td>
   <td>The sum of all elements, negative value.
   </td>
   <td>All negative numbers.
   </td>
  </tr>
  <tr>
   <td>testOverallNegativeResultRow()
   </td>
   <td>A row with an overall negative result
   </td>
   <td>A result that is less than 0
   </td>
   <td>Higher negative value than positive value
   </td>
  </tr>
  <tr>
   <td>testMostlyPositiveRow()
   </td>
   <td>A row with an overall positive result
   </td>
   <td>A result that is greater than 0
   </td>
   <td>Higher positive value than negative value
   </td>
  </tr>
  <tr>
   <td>testNegativeIndexRow()
   </td>
   <td>A row that is a negative index
   </td>
   <td>Should output an exception
   </td>
   <td>Negative row as an argument
   </td>
  </tr>
  <tr>
   <td>testNullFirstIndex()
   </td>
   <td>A row whose first index is null
   </td>
   <td>Should ignore the null.
   </td>
   <td>Row[0] = null
   </td>
  </tr>
  <tr>
   <td>testNullLastIndex()
   </td>
   <td>A row whose last index is null
   </td>
   <td>Should ignore the null.
   </td>
   <td>Row[n] = null
   </td>
  </tr>
</table>


**calculateColumnTotal(Values2D data, int column, int[] validRows)**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Arguments</strong>
   </td>
   <td><strong>Expected Result</strong>
   </td>
   <td><strong>Partition Covered</strong>
   </td>
  </tr>
  <tr>
   <td>testColumnValid()
   </td>
   <td>Valid data, valid column, valid rows
   </td>
   <td>The values should be summed up with proper columns specified
   </td>
   <td>data(0, limit -1)
   </td>
  </tr>
  <tr>
   <td>testColumnValidNegative()
   </td>
   <td>Valid data, valid column, valid rows, data includes negative values
   </td>
   <td>The values should be summed up with proper columns specified
   </td>
   <td>data(-limit+1, limit -1)
   </td>
  </tr>
  <tr>
   <td>testInvalidColumn()
   </td>
   <td>Valid data, invalid column, valid rows
   </td>
   <td>A proper Error should be displayed
   </td>
   <td>Column index beyond limits
   </td>
  </tr>
  <tr>
   <td>testInvalidRow()
   </td>
   <td>Valid data, valid column, invalid rows
   </td>
   <td>An error should occur
   </td>
   <td>Row index beyond limits
   </td>
  </tr>
</table>


**calculateRowTotal(Values2D data, int row, int[] col)**


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Changes</strong>
   </td>
   <td><strong>Expected Result</strong>
   </td>
   <td><strong>Partition Covered</strong>
   </td>
  </tr>
  <tr>
   <td>testRowTotalColumnValid()
   </td>
   <td>Valid data, valid column, valid rows
   </td>
   <td>The values should be summed up with proper columns specified
   </td>
   <td>data(0, limit -1)
   </td>
  </tr>
  <tr>
   <td>testRowTotalColumnValidWithNegative()
   </td>
   <td>Valid data, valid column, valid rows, data includes negative values
   </td>
   <td>The values should be summed up with proper columns specified
   </td>
   <td>data(-limit+1, limit -1)
   </td>
  </tr>
  <tr>
   <td>testRowTotalInvalidColumn()
   </td>
   <td>Valid data, invalid column, valid rows
   </td>
   <td>0, since no rows are added.
   </td>
   <td>Column index beyond limits
   </td>
  </tr>
  <tr>
   <td>testValidRowTotalWithValidColumnButNegativeRowIndex()
   </td>
   <td>Valid data, valid columns, invalid rows
   </td>
   <td>Error, since the row selected is out of bounds
   </td>
   <td>Row index out of bounds
   </td>
  </tr>
</table>


**getCumulativePercentages(KeyedValues data)**:


<table>
  <tr>
   <td><strong>Test Case</strong>
   </td>
   <td><strong>Arguments</strong>
   </td>
   <td><strong>Expected Result</strong>
   </td>
   <td><strong>Partition Covered</strong>
   </td>
  </tr>
  <tr>
   <td>testValidOrderedList()
   </td>
   <td>data contains an ordered list of numbers in ascending order
   </td>
   <td>The function should properly get the cumulative percentages of an ordered list
   </td>
   <td>data > 0 \
 \
KeyValues[0] &lt; 
<p>
KeyValues[1] &lt;
<p>
KeyValues[2] &lt; … 
   </td>
  </tr>
  <tr>
   <td>testValidUnorderedList
   </td>
   <td>data contains an unordered list of numbers
   </td>
   <td>The function should not get the cumulative percentages because the list is not ordered
   </td>
   <td>data > 0
<p>
 \
KeyValues[0] &lt; 
<p>
KeyValues[2] &lt;
<p>
KeyValues[1] &lt; …
   </td>
  </tr>
  <tr>
   <td>testInvalidList()
   </td>
   <td>data contains a list of characters, not numbers
   </td>
   <td>The function should not work since chars are not numbers and do not have percentages
   </td>
   <td>data = char
   </td>
  </tr>
  <tr>
   <td>testValidListWithNegativeNumbers()
   </td>
   <td>data contains a list with negative numbers
   </td>
   <td>The function should not work since the percentages should only be between 0 and 1.0
   </td>
   <td>data &lt; 0
   </td>
  </tr>
  <tr>
   <td>testNullArgumentList()
   </td>
   <td>data contains a list of arguments with some NULLs
   </td>
   <td>The function should not work since null data is not permitted in the function
   </td>
   <td>data = null
   </td>
  </tr>
  <tr>
   <td>testKeyOutsideBoundary()
   </td>
   <td>getValue() for a key that does not exist
   </td>
   <td>The function should not work
   </td>
   <td>key > max
   </td>
  </tr>
</table>


We felt that using mocking can be beneficial when simulating an object without actually initializing the object, where we would have to generate the entire object with all the values without mocking. However, we felt that the syntax and methods used for mocking can be complicated, since there is extra documentation to look up for the methods used for mocking. There are also limitations to using mocking we're it doesn't actually use the correct object, and thus doesn't throw the correct exceptions or function exactly the same as the real object would. We also found it to be less efficient and redundant when writing the mock object for testing KeyedValues since it requires the key index to be specified along with inputting the value in that key separately. 

# 4 How the teamwork/effort was divided and managed

After writing a general test strategy plan, we each picked one method from each class to write and code test cases for it. We then picked 1 more method from each class and did the test cases and code together. Overall, this method worked well in terms of saving time and splitting the work evenly, but issues with consistency between techniques for partitioning the class equivalence testing did occur. In the future, it is probably better to work on one thing together first as an example and then split up the different parts of the lab. 

# 5 Difficulties encountered, challenges overcome, and lessons learned

This lab has many difficulties. From the very start the logistics and complications of installing and configuring JUnit and for many, a new IDE(eclipse) took a huge chunk of time, it took hours of searching and configuring, and communicating with the team to get everyone ready to start coding and eventually get over this hurdle. The lab report itself was a challenge but not an extraordinarily difficult one, however, it was very time-consuming. The coding part was insightful and felt somewhat fulfilling but the vague documentation and somewhat loose lab specification lead us to be lost in more ways than one. Everything in this lab took a great amount of time and it really made us burn out while doing it. We learned a decent bit about the lab's content but it felt like it was a test of grit rather than knowledge. 

# 6 Comments/feedback on the lab itself

The problems on the lab were stated in the previous question, focusing on feedback for improvement we wanted to highlight a few key points:



* Do not underestimate the logistics of introducing a complete new work environment for a full team, in most classes setup is a lab in itself.
* Lots of repetitive/not so useful work, we had to repeat the same idea many times through tables, code comments, and lab report requirements. It really takes up a huge amount of time and effort just to check consistency over these things and to write them.
* Focus on giving sharp and precise instructions. We were left with some room for interpretation in many aspects of this lab, from either the methods specifications, to the expectations of the tests, however, we still had a feeling that something very specific was going to be graded, hopefully, that is not the case and the grading is more lenient but it would be good to have more precise guidelines on what to do.
