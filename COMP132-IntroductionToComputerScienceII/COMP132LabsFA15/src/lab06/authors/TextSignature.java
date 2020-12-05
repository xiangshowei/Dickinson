package lab06.authors;

import java.io.*;
import java.util.*;

/**
 * A "signature" of a text based on extracted "features". The features included
 * in the signature are:
 * <UL>
 * <LI>The average length of the words used
 * <LI>The average number of words per sentence
 * <LI>The Type-Token Ratio
 * <LI>The Hapax Legomana Ratio
 * <LI>The Sentence Complexity
 * </UL>
 * 
 * <p>
 * Each of these features are described in detail in the lab assignment and in
 * the comments in the class below.
 * 
 * @author Grant Braught
 * @version 8 March 2015
 */
public class TextSignature {
	/*
	 * Weights of each of the features to be used when computing the difference
	 * between two signatures.
	 */
	public static final double AVE_WORD_LENGTH_WEIGHT = 4.38;
	public static final double AVE_WORDS_PER_SENTENCE_WEIGHT = 1.0;
	public static final double TYPE_TOKEN_RATIO_WEIGHT = 147.0;
	public static final double HAPAX_LEGOMANA_RATIO_WEIGHT = 271.0;
	public static final double SENTENCE_COMPLEXITY_WEIGHT = 6.35;

	private String text;

	private String authorName;
	private double aveWordLength;
	private double aveWordsPerSentence;
	private double typeTokenRatio;
	private double hapaxLegomanaRatio;
	private double sentenceComplexity;

	/**
	 * Construct a new TextSignature for a work by the indicated author with the
	 * provided feature values.
	 * 
	 * @param authorName
	 *            the author's name.
	 * @param aveWordLength
	 *            the average word length in the text.
	 * @param aveWordsPerSentence
	 *            the average number of words per sentence in the text.
	 * @param typeTokenRatio
	 *            the type token ratio in the text.
	 * @param hapaxLegomanaRatio
	 *            the Hapax Legomana Ratio of the text.
	 * @param sentenceComplexity
	 *            the sentence complexity of the text.
	 */
	public TextSignature(String authorName, double aveWordLength,
			double aveWordsPerSentence, double typeTokenRatio,
			double hapaxLegomanaRatio, double sentenceComplexity) {
		this.authorName = authorName;
		this.aveWordLength = aveWordLength;
		this.aveWordsPerSentence = aveWordsPerSentence;
		this.typeTokenRatio = typeTokenRatio;
		this.hapaxLegomanaRatio = hapaxLegomanaRatio;
		this.sentenceComplexity = sentenceComplexity;
	}

	/**
	 * Construct a new TextSignature for the indicated author (possibly
	 * "unknown") using the text contained in the indicated file.
	 * 
	 * @param authorName
	 *            the author's name.
	 * @param filename
	 *            the name of the file containing the text from which the
	 *            signature will be computed.
	 * @throws IOException
	 *             if the file cannot be found or there is an error reading the
	 *             file.
	 */
	public TextSignature(String authorName, String filename) throws IOException {
		this.authorName = authorName;

		text = readFile(filename);

		this.aveWordLength = computeAveWordLength();
		this.aveWordsPerSentence = computeAveWordsPerSentence();
		this.typeTokenRatio = computeTypeTokenRatio();
		this.hapaxLegomanaRatio = computeHapaxLegomanaRatio();
		this.sentenceComplexity = computeSentenceComplexity();
	}

	/**
	 * Get the text that was read from the file.
	 * 
	 * @return the text that was read or null of no text was read.
	 */
	public String getText() {
		return text;
	}

	/*
	 * Read the text from the indicated file one line at a time and concatenate
	 * all of the lines into one big long string stored in the field "text".
	 * Lines should be separated by a space when concatenated into the string.
	 * Neither the strings read in nor the string returned should not be
	 * cleaned. If an exception occurs while reading the file it is propagated
	 * to the calling method.
	 */
	private String readFile(String filename) throws IOException {

		Scanner scr = new Scanner(new FileInputStream(filename));
		text = "";
		while (scr.hasNext()) {
			text += scr.nextLine() + " ";
		}//end while loop

		scr.close();
		return text;
	}

	/*
	 * Compute the average length of the words in text. The text should be split
	 * into Words using the TextTools class. Words should be cleaned using the
	 * TextTools class before their length is calculated.
	 */
	private double computeAveWordLength() {
		String[] words = TextTools.getWords(text);
		int numWords = words.length;
		int totalWordLength = 0;

		for (String word : words) {
			// clean up the text
			TextTools.cleanString(text);

			// number of characters in each word
			int wordLength = word.length();

			// get the total number of characters in the file
			totalWordLength += wordLength;
		}// end for each loop

		aveWordLength = (double) totalWordLength / numWords;

		return aveWordLength;
	}

	/*
	 * Compute the average number of words per sentence in the text. The text
	 * should be split into sentences using the TextTools class. Each sentence
	 * should be cleaned using the TextTools class before the number of words is
	 * counted.
	 */
	private double computeAveWordsPerSentence() {
		String[] words = TextTools.getWords(text);
		int numWords = words.length;

		String[] sentences = TextTools.getSentences(text);
		int numSentences = sentences.length;

		aveWordsPerSentence = (double) numWords / numSentences;

		return aveWordsPerSentence;
	}

	/*
	 * Compute the Type Token Ratio of the text. The Type Token Ratio is the
	 * ratio of the number of different words used in the text to the total
	 * number of words in the text. When computing the Type Token Ratio the
	 * capitalization of words is ignored (i.e. Book, BOOK and book are
	 * considered to be the same word). The TextTools class should be used to
	 * split the text into words. The TextTools class should be used to clean
	 * each word before it is used (this will ensure that capitalization and
	 * other meaningless differences are ignored).
	 */
	private double computeTypeTokenRatio() {
		String[] allWords = TextTools.getWords(text);
		int totalWords = allWords.length;

		ArrayList<String> diffWords = new ArrayList<String>();

		for (String word : allWords) {
			TextTools.cleanString(word);

			// if the ArrayList does not already contain the word that is
			// currently being looked at, add it to the ArrayList
			if (!diffWords.contains(word)) {
				diffWords.add(word);
			}// end if statement
		}// end for each loop

		typeTokenRatio = (double) diffWords.size() / totalWords;

		return typeTokenRatio;
	}

	/*
	 * Compute the Hapax Legomana Ratio for the text. The Hapax Legomana Ratio
	 * is the ratio of the number of words used exactly once in the text to the
	 * total number of words in the text. When computing the Hapax Legomana
	 * Ratio the capitalization of words is ignored (i.e. Book, BOOK and book
	 * are be considered to be the same word). The TextTools class should be
	 * used to split the text into words. The TextTools class should be used to
	 * clean each word before it is used (this will ensure that capitalization
	 * and other meaningless differences are ignored).
	 */
	private double computeHapaxLegomanaRatio() {
		String[] allWordsArr = TextTools.getWords(text);
		int totalWords = allWordsArr.length;

		ArrayList<String> allWordsAL = new ArrayList<String>();
		ArrayList<String> uniqueWords = new ArrayList<String>();

		for (int i = 0; i < allWordsArr.length; i++) {
			String curWord = TextTools.cleanString(allWordsArr[i]);

			// if the ArrayList of all of the words does NOT contain the current
			// word being looked at, add it to the ArrayList of all words and
			// ArrayList of unique words
			if (!allWordsAL.contains(curWord)) {
				allWordsAL.add(curWord);
				uniqueWords.add(curWord);
			}// end if statement
			
			// if the word being examined already exist in the ArrayList of all words, 
			// remove it from the ArrayList of unique words
			else {
				if (uniqueWords.contains(curWord)) {
					uniqueWords.remove(curWord);
				}// end if statement
			}// end else statement
		}// end for loop with counter

		hapaxLegomanaRatio = (double) uniqueWords.size() / totalWords;

		return hapaxLegomanaRatio;
	}

	/*
	 * Compute the Sentence Complexity of the text. The Sentence Complexity is
	 * simply the average number of phrases per sentence. The TextTools class
	 * should be used to split the text into sentences and to split each
	 * sentence into phrases. Note that phrases are identified by the
	 * punctuation in the sentences, thus the sentences must NOT be cleaned
	 * before they are split into phrases.
	 */
	private double computeSentenceComplexity() {
		String[] allPhrases = TextTools.getPhrases(text);
		int totalPhrases = allPhrases.length;

		String[] sentences = TextTools.getSentences(text);
		int totalSentences = sentences.length;

		sentenceComplexity = (double) totalPhrases / totalSentences;

		return sentenceComplexity;
	}

	/**
	 * Get the name of the author associated with this signature.
	 * 
	 * @return the author's name.
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Get the average length of the words used in the text.
	 * 
	 * @return the average word length.
	 */
	public double getAveWordLength() {
		return aveWordLength;
	}

	/**
	 * Get the average number of words per sentence in the text.
	 * 
	 * @return the average number of words per sentence.
	 */
	public double getAveWordsPerSentence() {
		return aveWordsPerSentence;
	}

	/**
	 * Get the type token ratio for the text.
	 * 
	 * @return the type token ratio.
	 */
	public double getTypeTokenRatio() {
		return typeTokenRatio;
	}

	/**
	 * Get the Hapax Legomana Ratio for the text.
	 * 
	 * @return the Hapax Legomana Ratio.
	 */
	public double getHapaxLegomanaRatio() {
		return hapaxLegomanaRatio;
	}

	/**
	 * Get complexity score for the text.
	 * 
	 * @return the sentence complexity.
	 */
	public double getSentenceComplexity() {
		return sentenceComplexity;
	}

	/**
	 * Compute a difference score between this text and that of another text.
	 * The difference score is computed as the sum of weighted absolute
	 * differences of the feature values. For each feature the absolute value of
	 * the difference between this text's value and the other text's value is
	 * found. This absolute difference is multiplied by the corresponding weight
	 * and totaled across all features. The weights to be used are given by the
	 * class constants. The smaller this value, the more similar the two texts
	 * are with respect to these features.
	 * 
	 * @param other
	 *            the TextSignature of a text to which this one should be
	 *            compared.
	 * @return the difference score between this author and the other author.
	 */
	public double computeDifference(TextSignature other) {
		double weightedAvgWordLengthDiff = AVE_WORD_LENGTH_WEIGHT * Math.abs(aveWordLength - other.getAveWordLength());
		double weightedAvgSentenceLengthDiff = AVE_WORDS_PER_SENTENCE_WEIGHT * Math.abs(aveWordsPerSentence - other.getAveWordsPerSentence());
		double weightedAvgTokenRatioDiff = TYPE_TOKEN_RATIO_WEIGHT * Math.abs(typeTokenRatio - other.getTypeTokenRatio());
		double weightedAvgHLRatioDiff = HAPAX_LEGOMANA_RATIO_WEIGHT * Math.abs(hapaxLegomanaRatio - other.getHapaxLegomanaRatio());
		double weightedAvgSentenceComplexityDiff = SENTENCE_COMPLEXITY_WEIGHT * Math.abs(sentenceComplexity - other.getSentenceComplexity());

		double total = weightedAvgWordLengthDiff + weightedAvgSentenceLengthDiff + weightedAvgTokenRatioDiff
				+ weightedAvgHLRatioDiff + weightedAvgSentenceComplexityDiff;

		return total;
	}
}