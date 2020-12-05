package comp132.examples.files.old;

import java.io.*;
import java.util.Scanner;

/**
 * Example illustrating how to write and read a text file.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 20, 2009
 */
public class TextFileEx {
	// To work with file in project base directory.
    private static final String FILE_NAME = "textfile.txt";

    // To work with file on my USB.
    // private static final String FILE_NAME = "/Volumes/BRAUGHT/textfile.txt";
    
    public static void main(String[] args) {
        writeTextFile();
        readTextFile();
    }

    /**
     * Write a text file using a FileWriter.
     */
    public static void writeTextFile() {

        /*
         * Open the file for writing. The second argument to the
         * FileOutputStream constructor indicates if the file should be appended
         * (true) or overwritten (false).
         * 
         * Creating a PrintWriter will throw an IOException if the file cannot
         * be opened (if appending) or created (if overwriting).
         */
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(FILE_NAME, false));
            
            pw.println("Bob Smith");
            pw.println(123456);
            pw.println(1234.56);
            pw.println("Jane Doe");
            pw.println(876543);
            pw.println(8765.43);

            /*
             * Close the PrintWriter when finished writing. Failure to close the
             * PrintWriter will likely result in not all of your data being written
             * to the file.
             */
            pw.close();
        }
        catch (IOException e) {
            System.out.println("Error opening or creating file " + FILE_NAME + ".");
            System.exit(-1);
        }
    }

    /**
     * Read a text file using a Scanner.
     */
    public static void readTextFile() {

        /*
         * Open the file for reading via a Scanner.  Creating a Scanner will
         * throw a FileNotFoundException if the file cannot be opened.
         */
        try {
            Scanner scr = new Scanner(new FileInputStream(FILE_NAME));
            
            /*
             * Read each line from the file and print it to the screen.
             */
            int count = 0;
            while (scr.hasNext()) {
                String line = scr.nextLine();
                System.out.println(count + ": " + line);
                count++;
            }

            // Close the file for good measure.
            scr.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error unable to open file " + FILE_NAME + ".");
            System.exit(-1);
        }
    }
}
