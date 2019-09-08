package com.aqeel.fibonacci;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;

/**
 * Generates sequences of Fibonacci numbers. This class expects a PrintStream to
 * write the output to.
 * 
 * @author asachwani
 *
 */
public class FibonacciGenerator {

	private final String separator;
	private static final BigInteger zerothFibonacci = new BigInteger("0");
	private static final BigInteger onethFibonacci = new BigInteger("1");
	private static final String RANGE_ERROR = "Please enter valid integers between 0 to 1000000 (Both Inclusive).\n";
	
	private PrintStream outputStream;

	/**
	 * @param seperator
	 *            User to seperate the Fibonacci numbers when printed.
	 * 
	 * @param outputStream
	 *            This is where we will write the series to. This can be any
	 *            OutputStream implementation
	 */
	public FibonacciGenerator(String seperator, PrintStream outputStream) {
		this.separator = seperator;
		this.outputStream = outputStream;
	}

	/**
	 * Prints `sequenceLength` fibonacci numbers. If sequenceLength == 10 ... it
	 * should print 0 1 1 2 3 5 8 13 21 34 55
	 * 
	 * The first two indexes that is the 0th and 1th are known and hard codes as
	 * 0 and 1 respectively.
	 * 
	 * @param sequenceLength
	 * @throws IOException
	 */
	public void printNFibonacci(int sequenceLength) throws IOException {
		if (sequenceLength < 0 || sequenceLength > 1000000) {
			outputStream.print(RANGE_ERROR);
			return;
		}

		// 0th fibonacci is zero
		BigInteger nMinus2 = zerothFibonacci;

		// 1th fibonacci us one
		BigInteger nMinus1 = onethFibonacci;

		// Print the 0th fibonacci if the required length is >= 0
		if (sequenceLength >= 0) {
			printSequence(nMinus2, sequenceLength != 0);
		}

		// Print the 1th fibonacci if the required length is >= 1
		if (sequenceLength >= 1) {
			printSequence(nMinus1, sequenceLength != 1);
		}

		// Print fibonacci sequence from second index onwards, this is done by
		// adding up the last two indexes.
		BigInteger ithNumber;
		for (int i = 2; i <= sequenceLength; i++) {
			ithNumber = nMinus2.add(nMinus1);
			nMinus2 = nMinus1;
			nMinus1 = ithNumber;
			// print the sequence. Add seperator with all except the last number in the sequence.
			printSequence(ithNumber, i != sequenceLength);
		}

	}

	/**
	 * Prints the sequence to the console. Set as protected so that extending
	 * classes can change the output format without changing the generation
	 * logic.
	 * 
	 * @param number
	 * 	number to print
	 * 
	 * @param addSeparator
	 *  add a separator after the number
	 * 
	 * @throws IOException
	 */
	protected void printSequence(BigInteger number, boolean addSeparator) throws IOException {
		if (addSeparator) {
			outputStream.print((number + separator));
		} else {
			outputStream.print((number));
		}
	}
}
