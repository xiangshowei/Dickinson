package lab06.authors;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * This program attempts to identify the author of an "unknown" text. The author
 * is identified by computing a "signature" for the text and comparing it to
 * similar signatures computed for texts where the author is known.
 * 
 * <p>
 * The "signature" of an author is formed by a collection of statistical
 * properties based on their writing. In this project an author's signature is
 * comprised of five values:
 * <UL>
 * <LI>The average length of the words used
 * <LI>The average number of words per sentence
 * <LI>The Type-Token Ratio
 * <LI>The Hapax Legomana Ratio
 * <LI>The Sentence Complexity
 * </UL>
 * 
 * <p>
 * Each of these statistics is computed by a sub-class of the FeatureCalculator
 * class and is described in detail in the lab assignment.
 * 
 * @author Grant Braught
 * @version 8 March 2015
 */
public class AuthorIdentifier {

	private static Scanner scr = new Scanner(System.in);
	private static final String SIGNATURES = "src/lab06/authors/documents/signatures.txt";

	/**
	 * The main method for the program. This method displays the menu and reads
	 * the user's choice. It invokes the appropriate method(s) based on the
	 * user's choice and repeats until the user exits the program.
	 * 
	 * @param args none.
	 */
	public static void main(String[] args) {

		SignatureCollection signatures = new SignatureCollection();

		boolean done = false;
		while (!done) {
			System.out.println("\nMenu:");
			System.out.println("------------------------------");
			System.out.println("A   Add Signature");
			System.out.println("D   Display Signatures");
			System.out.println("S   Save Signature File");
			System.out.println("L   Load Signature File");
			System.out.println("C   Clear Signatures");
			System.out.println("I   Identify Author");
			System.out.println("Q   Quit");
			System.out.println("------------------------------");
			System.out.print("Enter Option: ");

			String choice = scr.nextLine().toUpperCase();

			if (choice.equals("A")) {
				addSignature(signatures);
			}
			else if(choice.equals("D")){
				signatures.displaySignatures(); 
			}
			else if(choice.equals("S")){
				saveSignature(signatures, SIGNATURES);
			}
			else if(choice.equals("L")){
				loadSignature(signatures, SIGNATURES);
			}
			else if (choice.equals("C")) {
				signatures = new SignatureCollection();
			} 
			else if(choice.equals("I")){
				identifyAuthor(signatures);
			}
			else if (choice.equals("Q")) {
				System.out.println("Goodbye!");
				done = true;
			} 
			else {
				System.out.println("Unrecognized option: " + choice + ".\n\n");
			}
		}

		scr.close();
	}

	private static void addSignature(SignatureCollection signatures) {
		System.out.println("Enter the name of the author to be added: ");
		String lcAuthorName = scr.nextLine().toLowerCase();
		System.out.println("Enter a text file that the author has written: ");
		String lcAuthorWriting = scr.nextLine().toLowerCase();

		try{
			signatures.getSignature(lcAuthorName);
			signatures.loadSignatures(lcAuthorWriting);
		}
		catch (Exception e){
			System.out.println("Something went wrong when attempting to add a signature. ");
			System.out.println("Here's what happened: ");
			e.printStackTrace();
		}

	} 

	private static void saveSignature(SignatureCollection signatures, String filename) {
		try{
			signatures.saveSignatures(filename);
		}
		catch (Exception e){
			System.out.println("Something went wrong when attempting to add a signature. ");
			System.out.println("Here's what happened: ");
			e.printStackTrace();
		}
	}

	private static void loadSignature(SignatureCollection signatures, String filename) {
		try{
			signatures.loadSignatures(filename);
		}
		catch (Exception e){
			System.out.println("Something went wrong when attempting to add a signature. ");
			System.out.println("Here's what happened: ");
			e.printStackTrace();
		}
	}

	private static String identifyAuthor(SignatureCollection signatures) {
		System.out.println("Enter a text file that contains an author's work: ");
		String mysteryTxt = scr.nextLine().toLowerCase();

		TextSignature mysteryTextSig = signatures.getSignature(mysteryTxt);
		TextSignature mostSimilarAuthor = signatures.findMostSimilar(mysteryTextSig);

		return mostSimilarAuthor.getAuthorName();
	}

}
