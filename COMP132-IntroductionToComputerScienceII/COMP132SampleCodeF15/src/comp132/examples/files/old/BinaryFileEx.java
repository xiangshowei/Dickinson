package comp132.examples.files.old;

import java.io.*;

/**
 * Example illustrating how to write and read a binary file.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 20, 2009
 */
public class BinaryFileEx {
	// To work with file in the project base directory.
	private static final String FILE_NAME = "binaryfile.bin";

	// To work with file on my USB.
	// private static final String FILE_NAME = "/Volumes/BRAUGHT/binaryfile.bin";

	public static void main(String[] args) {
		writeBinaryFile();
		readBinaryFile();
	}

	/**
	 * Write a binary file using a DataOutputStream.
	 */
	public static void writeBinaryFile() {

		/*
		 * Open the file for writing. The second argument to the
		 * FileOutputStream constructor indicates if the file should be appended
		 * (true) or overwritten (false). Handle the possibility that there is
		 * an exception when trying to open the file.
		 */
		DataOutputStream das = null;
		try {
			das = new DataOutputStream(new FileOutputStream(FILE_NAME, false));
		} catch (IOException e) {
			System.out.println("Unable to open file " + FILE_NAME + ".");
			System.exit(-1);
		}

		/*
		 * Write some data to the file. The DataOutputStream class has a number
		 * of methods for writing different types of data.
		 */
		try {
			das.writeUTF("Bob Smith");
			das.writeInt(123456);
			das.writeDouble(1234.56);
			das.writeUTF("Jane Doe");
			das.writeInt(876543);
			das.writeDouble(8765.43);
		} catch (IOException e) {
			System.out.println("Error writing to " + FILE_NAME + ".");
			System.out.println("Data may be incomplete.");
		}

		/*
		 * Close the DataOutputStream when finished writing. Failure to close
		 * the DataOutputStream will likely result in not all of your data being
		 * written to the file.Note that closing the file can also generate an
		 * IOException so a try catch is needed here too.
		 */
		try {
			das.close();
		} catch (IOException e) {
			System.out.println("Error closing " + FILE_NAME + ".");
			System.out.println("Data may be incomplete.");
		}
	}

	/**
	 * Read a binary file using a DataInputStream.
	 */
	public static void readBinaryFile() {

		/*
		 * Open the file for reading. Handle the possibility that the file
		 * cannot be found.
		 */
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new FileInputStream(FILE_NAME));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file " + FILE_NAME + ".");
			System.exit(-1);
		}

		/*
		 * Read the data for each account and print it to the screen. Note that
		 * unlike the readLine method in the Scanner class for reading from a
		 * text file these methods can throw exceptions.
		 */
		try {
			while (dis.available() > 0) {
				String name = dis.readUTF();
				int num = dis.readInt();
				double bal = dis.readDouble();

				System.out.println(name);
				System.out.println(num);
				System.out.println(bal);
			}
		} catch (IOException e) {
			System.out.println("Error reading from " + FILE_NAME + ".");
			System.out.println("Data format is bad or data is missing.");
		}

		/*
		 * Close the file. Note that closing the file can also generate an
		 * IOException so a try catch is needed here too.
		 */
		try {
			dis.close();
		} catch (IOException e) {
			System.out.println("Error closing " + FILE_NAME + ".");
		}
	}
}
