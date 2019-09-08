package com.aqeel.fibonacci;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test the FibonacciGenerator. 
 * 
 * We will use a 
 * 
 * 
 * @author asachwani
 *
 */

@RunWith(Parameterized.class)
public class FibonacciGeneratorTest {
	private int input;
    private String expected;
	
	private FibonacciGenerator instance;
	
	// Use this instead of console to get output from FibonacciGenerator.
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/**
	 * This parameterized constructor should have as many params as there are columns in each row of the list returned in getTestData. 
	 * JUnit will initialize this class once for each row of that list.
	 * 
	 * @param input
	 * @param expected
	 */
	public FibonacciGeneratorTest(int input, String expected) {
		super();
		this.input = input;
		this.expected = expected;
	}

	/**
	 * Executes the setup steps to be executed before each test.
	 */
	@Before
	public void setUp() {
		instance = new FibonacciGenerator(" ", new PrintStream(outContent));
	}
	
	/**
	 * Build use cases to be executed against the test function.
	 * 
	 * @return
	 */
	@Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
            {-1, "System can only generate a fibonacci series from indexes 0 to 1000000.\n"}, // Lower Limit
            {1000001, "System can only generate a fibonacci series from indexes 0 to 1000000.\n"}, // Upper Limit
            {0, "0"}, // First Fibonacci that is the 0th one
            {1, "0 1"}, // First and second Fibonacci that is the 0th and 1th one.
            {10, "0 1 1 2 3 5 8 13 21 34 55"}, // Test the generation logic that builds starts with the first two and then uses the most recent two numbers to build the next fibonacci.
        });
    }
    
    /**
     * This test will be executed as many times as there are paramenters defines above using the getTestData method. Junit will initialize the expected and input params with entries from that list.
     * @throws IOException 
     */
    @Test
	public void testFibonacciGeneration() throws IOException {
		instance.printNFibonacci(input);
		Assert.assertEquals(expected, outContent.toString());
	}
}
