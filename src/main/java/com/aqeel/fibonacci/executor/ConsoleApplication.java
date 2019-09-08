package com.aqeel.fibonacci.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.aqeel.fibonacci.FibonacciGenerator;

/**
 * Entry point for executing the fibonacci generator.
 * 
 * @author asachwani
 *
 */
public class ConsoleApplication {

	private static final String HELP_STRING = "Usage: \r\n" //
			+ "Start with providing a separator [Type `s` for 'SPACE` OR `nl` for `NEW LINE`]. \r\n" //
			+ "Input an integer representing the total count of fibonacci numbers required. \r\n" //
			+ "Allowed values are the range of 0 to 1000000 (Both Inclusive). \r\n" //
			+ "Type \"help\" for help. \r\n" //
			+ "Type \"exit\" to exit. \r\n"; //

	private static final String SEPERATOR_PROMPT = "Enter Separator [Type `s` for 'SPACE` OR `nl` for `NEW LINE`]: ";
	private static final String INPUT_PROMPT = "Enter count of fibonacci series: ";
	private static final String NUMBER_FORMAT_ERROR = "Please enter valid integers between 0 to 1000000 (Both Inclusive).";

	public static void main(String[] args) {
		start();
	}

	/**
	 * Starts the execution of this application from CommandLine.
	 */
	private static void start() {
		System.out.println("Starting Fibonacci Series Generator.");
		System.out.println(HELP_STRING);

		try (Reader in = new InputStreamReader(System.in); BufferedReader reader = new BufferedReader(in)) {

			// Read the separator.
			String separator = readSeparator(reader);

			if (separator == null) {
				return;
			}

			// Initiate using the System.out stream. Used for console output.
			FibonacciGenerator instance = new FibonacciGenerator(separator, System.out);

			while (true) {
				System.out.print(INPUT_PROMPT);

				// Read user input from console. This method will wait while the
				// user
				// inputs data and presses enter.
				String lineValue = reader.readLine();

				// Check if termination is requested.
				if (processTerminationRequest(lineValue)) {
					break;
				}

				// Check if help is requested.
				if (processHelpRequest(lineValue)) {
					continue;
				}

				try {
					instance.printNFibonacci(Integer.valueOf(lineValue));
				} catch (NumberFormatException e) {
					System.out.println(NUMBER_FORMAT_ERROR);
				}

				System.out.println(" ");
			}

		} catch (IOException e) {
			System.out.println("Unable to read input from command line. Error: " + e.getMessage());
		}

	}

	/**
	 * Read and parse the separator input.
	 * 
	 * We only support `s` for space and `nl` for new-line.
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	protected static String readSeparator(BufferedReader reader) throws IOException {

		String separator = null;
		while (separator == null) {
			System.out.print(SEPERATOR_PROMPT);

			String lineValue = reader.readLine();

			if (processTerminationRequest(lineValue)) {
				break;
			}

			if (processHelpRequest(lineValue)) {
				continue;
			}

			if (lineValue.equalsIgnoreCase("s")) {
				separator = " ";
			} else if (lineValue.equalsIgnoreCase("nl")) {
				separator = "\n";
			} else {
				System.out.println("Unsupported Separator.");
			}
		}

		return separator;
	}

	/**
	 * This method parses the `exit` keyword that we use as a termination
	 * request.
	 * 
	 * @param value
	 * @return
	 */
	private static boolean processTerminationRequest(String value) {
		if (value.equalsIgnoreCase("exit")) {
			System.out.println("Exit requested. Terminating");
			return true;
		}

		return false;
	}

	/**
	 * This method parses the `help` keyword that we used to display the help
	 * text.
	 * 
	 * @param value
	 * @return
	 */
	private static boolean processHelpRequest(String lineValue) {
		if (lineValue.equalsIgnoreCase("help")) {
			System.out.println(HELP_STRING);
			return true;
		}

		return false;
	}
}
