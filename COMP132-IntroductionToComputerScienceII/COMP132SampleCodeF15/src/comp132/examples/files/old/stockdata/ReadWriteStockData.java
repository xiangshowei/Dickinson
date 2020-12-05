package comp132.examples.files.old.stockdata;

import java.io.*;

/**
 * Example of reading and writing a random access file.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 25, 2009
 */
public class ReadWriteStockData {

    private static final String PACKAGE = "src/comp132/examples/files/stockdata";
    private static final String FILE_NAME = "records.dat";
    private static final String RELATIVE_PATH = PACKAGE + "/" + FILE_NAME;

    /*
     * Each record consists of 4 double values (8 bytes each) and an
     * int value (4 bytes) for a total of 36 bytes per record.
     */
    private static final int RECORD_LENGTH = 36;

    private RandomAccessFile dataFile;

    /**
     * Constructor that opens the random access file for reading and writing.
     * 
     * @throws FileNotFoundException if the file cannot be created or opened.
     */
    public ReadWriteStockData() throws FileNotFoundException {
        dataFile = new RandomAccessFile(RELATIVE_PATH, "rw");
    }

    /**
     * Read the specified record number and display it on the screen.
     * 
     * @param recordNum the record number to display.
     * @throws IOException if there is a problem seekding to the record or
     * reading the file.
     */
    public void displayRecord(int recordNum) throws IOException {
        /*
         * Compute the location of the specified record number within the file
         * and set the file pointer to that location. The next read/write
         * operation will occur at the new location.
         */
        long loc = RECORD_LENGTH * recordNum;
        dataFile.seek(loc);

        double open = dataFile.readDouble();
        double close = dataFile.readDouble();
        double low = dataFile.readDouble();
        double high = dataFile.readDouble();
        int vol = dataFile.readInt();

        System.out.println("Record #" + recordNum);
        System.out.println("\t  Open: " + open);
        System.out.println("\t Close: " + close);
        System.out.println("\t   Low: " + low);
        System.out.println("\t  High: " + high);
        System.out.println("\tVolume: " + vol);
    }

    /**
     * Overwrite a record in the file with new data.
     * 
     * @param recordNum the record to overwrite.
     * @param open the new opening price
     * @param close the new closing price
     * @param low the new low price
     * @param high the new high price
     * @param volume the new volume
     * @throws IOException if there is a problem seeking to the record or writing 
     * the new data to the file.
     */
    public void writeRecord(int recordNum, double open, double close, double low, double high,
            int volume) throws IOException {
        long loc = RECORD_LENGTH * recordNum;
        dataFile.seek(loc);

        dataFile.writeDouble(open);
        dataFile.writeDouble(close);
        dataFile.writeDouble(low);
        dataFile.writeDouble(high);
        dataFile.writeInt(volume);
    }

    /**
     * Close the data file. This method should be called to ensure that all of
     * the changes to the file are written to the disk before the program
     * exists.
     * 
     * @throws IOException if the file cannot be closed.
     */
    public void closeFile() throws IOException {
        dataFile.close();
    }

    /**
     * Display, change and re-display a few records in order to demonstrate the use
     * of random file access.
     * 
     * @param args none
     */
    public static void main(String[] args) {

        ReadWriteStockData stockDataFile = null;
        try {
            stockDataFile = new ReadWriteStockData();
        }
        catch (FileNotFoundException e) {
            System.out.println("Unable to open the stock data file.");
            System.exit(-1);
        }
        
        try {
            stockDataFile.displayRecord(100);
            stockDataFile.displayRecord(213);
            
            stockDataFile.writeRecord(100, 11.11, 22.22, 7.77, 33.33, 44444);
            
            stockDataFile.displayRecord(100);
            stockDataFile.displayRecord(213);
        }
        catch (IOException e) {
            System.out.println("Error reading/writing stock data records.");
        }
        
        try {
            stockDataFile.closeFile();
        }
        catch (IOException e) {
            System.out.println("Unable to close the stock data file.");
            System.exit(-1);
        } 
    }
}
