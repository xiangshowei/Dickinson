package comp132.examples.files.old.stockdata;

import java.io.*;
import java.util.Random;

/**
 * This class generates a random data file that is used for demonstrating
 * random access files.  The data file created has randomly generated values
 * for Open/Close/Low/High stock prices plus trading volume.  The prices
 * are written as double values (8 bytes each) the volume is written as an
 * int (4 bytes).  So each record is 4*8 + 4 = 36 bytes long.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 24, 2009
 */
public class MakeRandomStockData {

    private static final String PACKAGE = "src/comp132/examples/files/stockdata";
    private static final String FILE_NAME = "records.dat";
    private static final String RELATIVE_PATH = PACKAGE + "/" + FILE_NAME;

    private static final int NUM_RECORDS = 300;
    
    public static void main(String[] args) {
        DataOutputStream das = null;
        try {
            das = new DataOutputStream(new FileOutputStream(RELATIVE_PATH, false));
        }
        catch (IOException e) {
            System.out.println("Unable to open file " + RELATIVE_PATH + ".");
            System.exit(-1);
        }

        try {
            Random rnd = new Random();

            for (int i = 0; i < NUM_RECORDS; i++) {  
                /*
                 * Generate random but plausible data for the low/high/open/close prices
                 * and trading volume and write them to the file.
                 */
                double low = rnd.nextInt(100) + rnd.nextInt(100) / 100.0;
                double high = low + 1 + rnd.nextInt(100) + rnd.nextInt(100) / 100.0;
                double open = low + rnd.nextInt((int)(high - low)) + rnd.nextInt(100) / 100.0;
                double close = low + rnd.nextInt((int)(high - low)) + rnd.nextInt(100) / 100.0;
                int vol = rnd.nextInt(100000);

                das.writeDouble(open);
                das.writeDouble(close);
                das.writeDouble(low);
                das.writeDouble(high);
                das.writeInt(vol);
            }
        }
        catch (IOException e) {
            System.out.println("Error writing to " + FILE_NAME + ".");
            System.out.println("Data may be incoplete.");
        }

        try {
            das.close();
        }
        catch (IOException e) {
            System.out.println("Error closing " + FILE_NAME + ".");
            System.out.println("Data may be incoplete.");
        }
    }
}
