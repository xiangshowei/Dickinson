package comp132.examples.files;

import java.io.*;

/**
 * Some sample code that illustrates the reading and writing of binary files
 * using Java.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 29, 2015
 */
public class BinaryFileExamples {

	/**
	 * Illustrate the basic process of writing data to a binary file.
	 */
	public static void writeBinaryFile(String filename) throws IOException {

		/*
		 * This needs to be declared here and initialized to null so that it can
		 * be used not only in the try block but also in the finally block to
		 * close the file.
		 */
		DataOutputStream dos = null;

		try {
			/*
			 * Create a FileOutputStream that can write bytes to the file
			 * SampleFile.bin. The second parameter indicates if new output
			 * should be appended to the file. True indicates that new output
			 * will be added to the end of the existing file. False indicates
			 * that if the file exists it will be overwritten (replaced). NOTE:
			 * This is exactly the same as with text files!
			 */
			FileOutputStream fos = new FileOutputStream(filename, false);

			/*
			 * Create a DataOutputStream that will output the bytes representing
			 * the Java data values to the FileOutputStream, which will handle
			 * writing them into the file.
			 */
			dos = new DataOutputStream(fos);

			/*
			 * Write values of various Java data types to the file. Each type
			 * uses the same number of bytes in the file as its corresponding
			 * Java data type uses in memory.
			 */
			dos.writeByte(65); // 1 byte
			dos.writeShort(12345); // 2 bytes
			dos.writeInt(123456789); // 4 bytes
			dos.writeLong(999987654321L); // 8 bytes - Note: L indicates long
			dos.writeFloat(3.14F); // 4 bytes - Note: F indicates float
			dos.writeDouble(2.718); // 8 bytes

			/*
			 * Strings are written to binary files in UTF-8 format, which is a
			 * way to encode all of the UNICODE characters. A string written
			 * with writeUTF is preceded by a short value (2 bytes) indicating
			 * the number of bytes used to represent the string. Following the
			 * short value, each character in the string is encoded using 1 to 4
			 * bytes depending on its UNICODE value. Note: The first 127 ASCII
			 * characters require only one byte each. So if the string contains
			 * only ASCII characters then the number of bytes is equal to the
			 * length of the string.
			 */
			dos.writeUTF("This is some text to write to the file."); // 39
		} finally {
			/*
			 * The code in the finally clause executes either when the code in
			 * the try completes or when it throws an exception. Thus, the close
			 * statement will be executed even if this method terminates by
			 * having an exception thrown.
			 */
			if (dos != null) {
				dos.close();
			}
		}
	}

	/**
	 * Illustrate the basic process of reading data from binary file. This
	 * method reads back the data written by the writeBinaryFile method.
	 */
	public static void readBinaryFile(String filename) throws IOException {

		DataInputStream dis = null;

		try {
			/*
			 * Create a FileInputStream that can read bytes from the file
			 * SampleFile.bin. NOTE: This is exactly the same as with text
			 * files!
			 */
			FileInputStream fis = new FileInputStream(filename);

			/*
			 * Create a DataInputStream that will interpret the bytes read from
			 * the FileInputStream as Java data values.
			 */
			dis = new DataInputStream(fis);

			byte b = dis.readByte();
			System.out.println("byte:   " + b);
			short s = dis.readShort();
			System.out.println("short:  " + s);
			int i = dis.readInt();
			System.out.println("int:    " + i);
			long l = dis.readLong();
			System.out.println("long    " + l);
			float f = dis.readFloat();
			System.out.println("float:  " + f);
			double d = dis.readDouble();
			System.out.println("double: " + d);
			String str = dis.readUTF();
			System.out.println("String: " + str);
		} finally {
			/*
			 * Always be sure to close the stream. But be sure never to cross
			 * them :)
			 */
			if (dis != null) {
				dis.close();
			}
		}
	}

	/**
	 * Invoke the methods in this class to demonstrate writing and reading of
	 * binary files.
	 */
	public static void main(String[] args) {
		try {
			writeBinaryFile("SampleFile.bin");
		} catch (FileNotFoundException e) {
			/*
			 * Notice that there are multiple catch statements, one for each
			 * type of exception. This would allow the calling code to take
			 * different actions depending upon the type of exception. For
			 * example, we might want to prompt for another filename if the file
			 * could not be opened, but we might want to try again if there was
			 * an error writing.
			 */
			System.out.println("Unable to open SampleFile.bin for writing.");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error writing data to SampleFile.bin.");
			System.out.println(e.getMessage());
		}

		try {
			readBinaryFile("SampleFile.bin");
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open SampleFile.bin for reading.");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading data from SampleFile.bin.");
			System.out.println(e.getMessage());
		}
	}
}
