package lab06.authors;

import java.text.BreakIterator;
import java.util.*;

/**
 * A collection of static methods that perform text processing tasks.
 * 
 * @author Grant Braught
 * @version 10 March 2015
 */
public class TextTools {

	/**
	 * Get the sentences from the text. An array of Strings is generated with
	 * each element containing one sentence from the full text.
	 * 
	 * Note: Parsing sentences exactly is more difficult than one might at first
	 * think. Thus, the sentence parsing is approximate. So not every entry in
	 * the returned array will contain a complete sentence. However, they will
	 * be close enough for this assignment and thus we will assume that each
	 * entry is a complete sentence.
	 * 
	 * @param text the text to be split into sentences.
	 * @return an array of the sentences in the text.
	 */
	public static String[] getSentences(String text) {
		return (getChunks(text, BreakIterator.getSentenceInstance(Locale.US)));
	}

	/**
	 * Get the phrases from the text. An array of Strings is generated with each
	 * element containing one phrase from the full text.
	 * 
	 * Note: Parsing phrases exactly is quite difficult. So not every entry in
	 * the returned array will contain a complete or accurate phrase. However,
	 * they will be close enough for this assignment and thus we will assume
	 * that each entry is a complete and accurate phrase.
	 * 
	 * @return an array of the phrases in the text.
	 */
	public static String[] getPhrases(String text) {
		String[] sentences = getSentences(text);

		ArrayList<String> allPhrases = new ArrayList<String>();
		for (String s : sentences) {
			String[] phrases = s.split("[:,;()]");
			for (String p : phrases) {
				allPhrases.add(p.trim());
			}
		}

		return allPhrases.toArray(new String[] {});
	}

	/**
	 * Get the individual words from the text. An array of Strings is generated
	 * with each element containing one word from the full text.
	 * 
	 * Note: Parsing words exactly is more difficult than one might at first
	 * think. Thus, the word parsing is approximate. So not every entry in the
	 * returned array will contain a complete and accurate single word. However,
	 * they will be close enough for this assignment and thus we will assume
	 * that each entry is a complete and accurate single word.
	 * 
	 * @param text the text to be split into words.
	 * @return an array of the words in the text.
	 */
	public static String[] getWords(String text) {
		return (getChunks(text, BreakIterator.getWordInstance(Locale.US)));
	}

	/**
	 * Cleans the string.  This method performs the following operations:
	 * <UL>
	 * <LI>Removes everything except the letters, spaces and digits
	 * <LI>Makes the entire string lower case
	 * <LI>Compressed any multiple space sequences down to a single space.
	 * </UL>
	 * 
	 * @param str the string to be cleaned.
	 * @return the cleaned string.
	 */
	public static String cleanString(String str) {
		StringBuffer sb = new StringBuffer(str);

		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
				sb.delete(i, i + 1);
				i--;
			}
		}
		
		// Change newlines into spaces.
		int count = 1;
		while (count > 0) {
			count = replaceAll(sb, "\n", " ");
		}

		// Change tabs into spaces.
		count = 1;
		while (count > 0) {
			count = replaceAll(sb, "\t", " ");
		}

		// Change multiple spaces into single spaces.
		count = 1;
		while (count > 0) {
			count = replaceAll(sb, "  ", " ");
		}

		return sb.toString().trim().toLowerCase();
	}

	/*
	 * Replace all occurrences of f in sb by r.
	 */
	private static int replaceAll(StringBuffer sb, String f, String r) {
		int count = 0;
		int i = sb.indexOf(f);
		while (i != -1) {
			sb.replace(i, i + f.length(), r);
			i = sb.indexOf(f);
			count++;
		}
		return count;
	}

	/*
	 * Eliminate extraneous characters that appear in some of the text files
	 * that make the task more difficult than necessary.
	 */
	private static String strip(String str) {
		StringBuffer sb = new StringBuffer(str);

		replaceAll(sb, "�", "  ");
		int count = 1;
		while (count > 0) {
			count = replaceAll(sb, "  ", " ");
		}

		replaceAll(sb, "_", "");

		return sb.toString();
	}

	/*
	 * Helper method used by a few of the above methods for splitting the text
	 * into sentences or words.
	 */
	private static String[] getChunks(String text, BreakIterator cit) {
		cit.setText(text);
		ArrayList<String> chunks = new ArrayList<String>();

		int chunkStart = 0;
		int chunkEnd = cit.next();
		while (chunkEnd != BreakIterator.DONE) {
			String chunk = strip(text.substring(chunkStart, chunkEnd)).trim();
			for (int i = 0; i < chunk.length(); i++) {
				if (Character.isLetter(chunk.charAt(i))) {
					chunks.add(chunk);
					break;
				}
			}
			chunkStart = chunkEnd;
			chunkEnd = cit.next();
		}

		return (String[]) chunks.toArray(new String[] {});
	}
}
