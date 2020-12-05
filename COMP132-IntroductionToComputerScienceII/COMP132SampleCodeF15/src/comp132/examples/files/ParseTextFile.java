package comp132.examples.files;

import java.io.*;
import java.util.Scanner;

public class ParseTextFile {

	public static void main(String[] args) {
		String path = "src/comp132/examples/files/samples/";
		String inFile = path + "DataFile.txt";
		String outFile = path + "ResultsFile.txt";

		try {
			// Setup to read the input file
			FileInputStream fis = new FileInputStream(inFile);
			Scanner scr = new Scanner(fis);

			/*
			 * Set the delimiter(s) to be used by the Scanner to divide the
			 * tokens. Each character listed inside [...] is used as a
			 * delimiter. Here both ',' (comma) and '\n' (newline) are used as
			 * delimiters between tokens. We need both because the final double
			 * one each line is not delimited from the next town name by a comma
			 * but instead by a newline.
			 */
			scr.useDelimiter("[,\n]");

			// Setup to write to the output file.
			FileOutputStream fos = new FileOutputStream(outFile, false);
			PrintWriter pw = new PrintWriter(fos);

			/*
			 * Each time through the loop we read the next line of data,
			 * calculate the average and write out the town name and the
			 * average.
			 */
			while (scr.hasNext()) {
				String town = scr.next();
				int count = scr.nextInt();
				double total = 0;
				for (int i = 0; i < count; i++) {
					total = total + scr.nextDouble();
				}
				double ave = total / count;

				pw.println(town + "," + ave);
			}

			// Close the input and output files.
			scr.close();
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
