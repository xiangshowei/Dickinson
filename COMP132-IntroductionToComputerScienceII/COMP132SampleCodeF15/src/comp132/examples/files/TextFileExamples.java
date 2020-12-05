package comp132.examples.files;

import java.io.*;
import java.util.*;

/**
 * Some sample code that illustrates the reading and writing of text files using
 * Java.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 29, 2015
 */
public class TextFileExamples {

	/**
	 * Illustrate the basic process of writing lines of text to a file.
	 */
	public static void writeTextFile(String filename)
			throws FileNotFoundException {

		/*
		 * Create a FileOutputStream that can write bytes to the file
		 * SampleFile.txt. The second parameter indicates if new output should
		 * be appended to the file. True indicates that new output will be added
		 * to the end of the existing file. False indicates that if the file
		 * exists it will be overwritten (replaced).
		 * 
		 * Creating a FileOutputStream will throw a FileNotFoundException if it
		 * is not possible to create the new output file. A
		 * FileNotFoundException is a checked exception so it must be caught or
		 * explicitly propagated in the method signature. Usually it will be
		 * propagated from the method doing the reading and handled in one of
		 * the calling methods.
		 */
		FileOutputStream fos = new FileOutputStream(filename, false);

		/*
		 * Create a PrintWriter that will use ASCII values to translate Java
		 * data values into bytes to the FileOutputStream, which will handle
		 * writing them into the file.
		 */
		PrintWriter pw = new PrintWriter(fos);

		/*
		 * Can use print or println on the PrintWriter object to output Java
		 * values to the text file.
		 * 
		 * Can you predict what the file contents will be?
		 */
		pw.println("This is a basic ASCII text file.");
		pw.print("Even numbers like ");
		pw.print(123);
		pw.println(" and 3.14 are put into ASCII.");
		pw.println("The End!");

		/*
		 * The file must be closed when all writing is complete to be sure that
		 * all of the bytes are sent to the file.
		 */
		pw.close();
	}

	/**
	 * Illustrate the basic process of reading lines from a text file. This
	 * method will read each line from the file and print it preceded by a line
	 * number.
	 */
	public static void readTextFile(String filename)
			throws FileNotFoundException {
		/*
		 * Create a new FileInputStream that can read bytes from the file
		 * SampleFile.txt.
		 * 
		 * Creating a FileInputStream will throw a FileNotFoundException if the
		 * file does not exist. A FileNotFoundException is a checked exception
		 * so it must be caught or explicitly propagated in the method
		 * signature. Usually it will be propagated from the method doing the
		 * reading and handled in one of the calling methods.
		 */
		FileInputStream fis = new FileInputStream(filename);

		/*
		 * Create a new scanner that will translate the ASCII values given by
		 * the bytes from the FileInputStream into Java data values.
		 */
		Scanner scr = new Scanner(fis);

		/*
		 * While there are more lines in the file, read one in and print it out
		 * preceded by the line number.
		 */
		int lineNum = 0;
		while (scr.hasNext()) {
			String line = scr.nextLine();
			System.out.println(lineNum + ": " + line);
			lineNum++;
		}

		/*
		 * Close the scanner because we are completely done with it.
		 */
		scr.close();
	}

	/**
	 * Invoke the methods in this class to demonstrate writing and reading of
	 * text files.
	 */
	public static void main(String[] args) {
		try {
			/*
			 * Call the writeTextFile method and handle the exception here. In
			 * practice the try/catch would appear at whatever point something
			 * sensible can be done about the problem (e.g. display an error,
			 * prompt the user for a new name, etc...)
			 */
			writeTextFile("SampleFile.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Error opening SampleFile.txt for writing.");
			System.out.println(e.getMessage());
		}

		try {
			readTextFile("SampleFile.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Error opening SampleFile.txt for reading.");
			System.out.println(e.getMessage());
		}
	}
}
