package lab06.authors;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * A collection of AuthorSignatures.
 * 
 * @author Grant Braught
 * @version 8 March 2015
 */
public class SignatureCollection {

	private HashMap<String, TextSignature> sigList;
	private String authorName;
	private double avgWordLength;
	private double avgSentenceLength;
	private double typeTokenRatio;
	private double hapaxLegomanaRatio;
	private double sentenceComplexity;

	/**
	 * Construct a new empty SignatureCollection.
	 */
	public SignatureCollection() {
		sigList = new HashMap<String, TextSignature>();
		authorName = null;
		avgWordLength = 0;
		avgSentenceLength = 0;
		typeTokenRatio = 0;
		hapaxLegomanaRatio = 0;
		sentenceComplexity = 0;
	}

	/**
	 * Save all of the AuthorSignatures in this SignatureCollection into the
	 * specified file. If the file already exists its contents should be
	 * replaced with the AuthorSignatures in this collection. If an exception
	 * occurs while writing the file, the exception is propagated to the
	 * calling method.
	 * 
	 * <p>
	 * The AuthorSignatures should be saved into the file in plain text. Each
	 * line of the file will contain a single signature in the following format:
	 * <p>
	 * <UL>
	 * author name,AWL,AWS,TTR,HLR,SC
	 * </UL>
	 * <p>
	 * Where:
	 * <UL>
	 * <LI>AuthorName = the author's first and last name, all lower case with
	 * one space between.
	 * <LI>AWL = Average word length
	 * <LI>AWS = Average words per sentence
	 * <LI>TTR = Type Token Ratio
	 * <LI>HLR = Hapax Legomana Ratio
	 * <LI>SC = Sentence Complexity
	 * </UL>
	 * For example the file might contain:
	 * <p>
	 * <UL>
	 * 
	 * <pre>
	 * agatha christie,4.40212537354,0.103719383127,0.0534892315963,10.0836888743,1.90662947161
	 * charles dickens,4.34760725241,0.0803220950584,0.0390662700499,16.2613453121,2.87721723105
	 * ... etc ...
	 * </pre>
	 * 
	 * </UL>
	 * 
	 * @param filename the name of the file into which the AuthorSignatures are
	 *            to be written.
	 * @throws IOException if the file cannot be opened or there is a problem
	 *             writing the file.
	 */
	public void saveSignatures(String filename) throws IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(filename, false));
		
		for(TextSignature ts : sigList.values()){
			pw.write(ts.getAuthorName() + "," + ts.getAveWordLength()+ "," +  ts.getAveWordsPerSentence() + "," 
		+ ts.getTypeTokenRatio() + "," + ts.getHapaxLegomanaRatio() + "," + ts.getSentenceComplexity());
		}

		pw.close();
	}

	/**
	 * Add all of the AuthorSignatures contained in the specified file into this
	 * SignatureCollection. If a signature to be added matches an author already
	 * in this collection, then the new signature replaces the existing one.
	 * 
	 * The file will be a plain text file containing each AuthorSignature on a
	 * single line. See the <code>saveSignatures</code> method for the full
	 * details of the file format.
	 * 
	 * @see #saveSignatures(String)
	 * @see #addSignature(AuthorSignatureOld)
	 * 
	 * @param filename the name of the file from which to read the
	 *            AuthorSignatures.
	 * 
	 * @throws IOException if the file cannot be found or opened.
	 * @throws ParseException if there is a problem parsing the file contents.
	 *             For example a line may contain an invalid value or not enough
	 *             values. The errorOffset value in the ParseException should
	 *             indicate the line number in the signature file where the
	 *             error occurred.
	 */
	public void loadSignatures(String filename) throws IOException, ParseException {
		int lineNum = 1;
		Scanner scr = new Scanner(new FileInputStream(filename));
		while(scr.hasNextLine()){
			//get the current line of the file
			String curLine = scr.nextLine();

			//get the author signatures delimited by commas
			String[] authorInfo = curLine.split(",");

			//parse the current line that contains the author information
			try{
				authorName = authorInfo[0];
				avgWordLength = Double.parseDouble(authorInfo[1]);
				avgSentenceLength = Double.parseDouble(authorInfo[2]);
				typeTokenRatio = Double.parseDouble(authorInfo[3]);
				hapaxLegomanaRatio = Double.parseDouble(authorInfo[4]);
				sentenceComplexity = Double.parseDouble(authorInfo[5]);

				//storing the information that were parsed into a TextSignature object
				TextSignature ts = new TextSignature(authorName, avgWordLength, avgSentenceLength,typeTokenRatio, hapaxLegomanaRatio,sentenceComplexity );
				addSignature(ts);
				//increment line count
				lineNum++;

			}//end try block

			catch (Exception e){
				throw new ParseException("there was a problem reading the file", lineNum);
			}// end catch block
		}// end while loop

		scr.close(); 
	}

	/**
	 * Get the number of AuthorSignatures that are in this Collection.
	 * 
	 * @return the size of this collection.
	 */
	public int getSize() {
		return sigList.size();
	}

	/**
	 * Get the AuthorSignature for the specified author. If no signature exists
	 * for the specified author this method will return null.
	 * 
	 * @param authorName the name of the author for which to get the signature.
	 * @return the AuthorSignature for the author or null if no signature exists
	 *         for the author.
	 */
	public TextSignature getSignature(String authorName) {
		return sigList.get(authorName);
	}

	/**
	 * Add the provided AuthorSignature to this collection. If the provided
	 * signature matches an author who already has a signature in this
	 * collection the new signature replaces the old one.
	 * 
	 * @param sig the AuthorSignature to be added to the collection.
	 */
	public void addSignature(TextSignature sig) {
		sigList.put(sig.getAuthorName(), sig);
	}

	/**
	 * Find and return the AuthorSignature from the collection that is the most
	 * similar (i.e. smallest difference) from the provided signature.
	 * 
	 * @param match the signature for which to find a match.
	 * @return the AuthorSignature that most closely matches the provided
	 *         signature.
	 */
	public TextSignature findMostSimilar(TextSignature match) {
		ArrayList<TextSignature> textSigAL = new ArrayList<TextSignature>();

		//move the TextSignatures into an ArrayList
		for(TextSignature ts : sigList.values()){
			textSigAL.add(ts);
		}// end for each loop

		//get the first TextSignature and compute the difference between it and the match TextSignature
		TextSignature mostSimTextSig = textSigAL.get(0);
		double smallestDiff = mostSimTextSig.computeDifference(match);
		
		//go through the ArrayList and compute the difference between each TextSignature and the match TextSignature
		for(int i = 0; i < textSigAL.size(); i++){
			TextSignature curTextSig = textSigAL.get(i); 
			
			double curDiff = curTextSig.computeDifference(match);
			
			//update the TextSignature with that has the most similarities
			if(curDiff < smallestDiff){
				mostSimTextSig = curTextSig;
			}// end if statement
		}// end for loop with counter
		
		return mostSimTextSig;
	}

	/**
	 * Display the contents of this SignatureCollection on the screen.
	 */
	public void displaySignatures() {
		DecimalFormat df = new DecimalFormat("0.0000");
		System.out.println("Signatures:");
		for (TextSignature cur : sigList.values()) {
			System.out.println("\n" + cur.getAuthorName());
			System.out.print("\t" + df.format(cur.getAveWordLength()));
			System.out.print("\t" + df.format(cur.getAveWordsPerSentence()));
			System.out.print("\t" + df.format(cur.getTypeTokenRatio()));
			System.out.print("\t" + df.format(cur.getHapaxLegomanaRatio()));
			System.out.println("\t" + df.format(cur.getSentenceComplexity()));
		}
	}
}
