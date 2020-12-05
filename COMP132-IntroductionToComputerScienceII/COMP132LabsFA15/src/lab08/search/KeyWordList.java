package lab08.search;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class represents a searchable list of keywords. Each search returns a
 * list of PageInfo objects representing all of the web pages that are
 * associated with the keywords that were searched for.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Apr 13, 2010
 */
public class KeyWordList {

	private ArrayList<KeyWord> keyWordList;

	/**
	 * Construct a new, empty, KeyWordList.
	 */
	public KeyWordList() {
		keyWordList = new ArrayList<KeyWord>();
	}

	/**
	 * Construct a new KeyWordList based on the contents of the specified file.
	 * The file is assumed to contain information produced by the WebCrawler.
	 * The files produced by WebCrawler have 4 lines for each web page. The
	 * first line is the title of the page, the second line is the URL of the
	 * page, the third line is a comma delimited list of all of the keywords
	 * associated with the page and the fourth line is an integer indicating the
	 * number of incoming links that were found to the page.
	 * 
	 * @param filename
	 *            the name of the file containing the web page information.
	 * @throws FileNotFoundException
	 *             if the file cannot be found.
	 */
	public KeyWordList(String filename) throws FileNotFoundException {
		keyWordList = new ArrayList<KeyWord>();
		Scanner scr = new Scanner(new FileInputStream(filename));
		while (scr.hasNext()) {
			// first line
			String title = scr.nextLine();
			// second line
			String url = scr.nextLine();
			// third line containing all of the keywords
			String keywords = scr.nextLine();
			// number of incoming links
			int linkCount = Integer.parseInt(scr.nextLine());

			// storing the things that we read in into an PageInfo object
			PageInfo pi = new PageInfo(title, url, linkCount);

			// creates an array of Strings where each index is a keyword
			String[] keyWordsArr = keywords.split(",");
			// for each keyword in the Array
			for (String keyword : keyWordsArr) {
				// create a KeyWord object
				KeyWord kw = new KeyWord(keyword);
				// first time the keyword shows up for the PageInfo we're looking at
				if (!keyWordList.contains(kw)) {
					// add the PageInfo to the keyword
					kw.addPage(pi);
					// add the keyword to the ArrayList
					keyWordList.add(kw);
				} else {
					// if the current keyword is the same as an existing keyword in the ArrayList,
					// add the page info of the current keyword to the existing keyword
					int kwIndex = keyWordList.indexOf(kw);
					keyWordList.get(kwIndex).addPage(pi);
				}// end else statement
			}// end for each loop
		}// end while loop

		scr.close();
	}

	/**
	 * Add a KeyWord to this KeyWordList.
	 * 
	 * @param word
	 *            the KeyWord to add.
	 */
	public void addKeyWord(KeyWord word) {
		keyWordList.add(word);
	}

	/**
	 * Get an ArrayList containing all of the KeyWords that appear in this list.
	 * 
	 * @return the list of KeyWords
	 */
	public ArrayList<KeyWord> getKeyWords() {
		return keyWordList;
	}

	/**
	 * Sort this KeyWordList into alphabetical order.
	 */
	public void sort() {
		Collections.sort(keyWordList);
	}

	/**
	 * Get a list of PageInfo objects representing all of the web pages that are
	 * associated with the specified keyword. The list that is returned should
	 * be sorted into order by decreasing number of incoming links with ties
	 * broken by (case insensitive) alphabetical order of the page title. NOTE:
	 * this is the order imposed by the compareTo method in the PageInfo class.
	 * 
	 * @param keyword
	 *            the keyword for which to search.
	 * @return a sorted list of the PageInfo objects associated with the
	 *         keyword.
	 */
	public ArrayList<PageInfo> search(String keyword) {
		// sort keyWordList so binary search will work
		this.sort();

		KeyWord targetKW = new KeyWord(keyword);
		int index = Collections.binarySearch(keyWordList, targetKW);

		// get back the ArrayList of PageInfo for targetKW
		ArrayList<PageInfo> pageInfoForTargerKW = keyWordList.get(index).getPages();

		// sort the PageInfo ArayList so that it is in order
		Collections.sort(pageInfoForTargerKW);

		return pageInfoForTargerKW;
	}

	/**
	 * Get a list of PageInfo objects representing the web pages that are
	 * associated with ALL of the specified keywords. That is, is if keywords
	 * were {"cat", "dog"} then the list returned would contain only those pages
	 * that are associated with both the keyword "cat" and the keyword "dog".
	 * The list that is returned should be sorted into order by decreasing
	 * number of incoming links with ties broken by (case insensitive)
	 * alphabetical order of the page title. NOTE: this is the order imposed by
	 * the compareTo method in the PageInfo class.
	 * 
	 * @param keyword
	 *            the keyword for which to search.
	 * @return a sorted list of the PageInfo objects associated with all of the
	 *         keywords.
	 */
	public ArrayList<PageInfo> searchAll(String[] keywords) {
		ArrayList<PageInfo> masterList = getMasterList(keywords);

		for (String keyword : keywords) {
			ArrayList<PageInfo> pageInfoForCurKW = this.search(keyword);
			for (PageInfo pi : masterList) {
				if(!pageInfoForCurKW.contains(pi)) {
					masterList.remove(pi);
				}// end if statement
			}//end for each loop
		}// end for loop with counter

		Collections.sort(masterList);

		return masterList;
	}

	/**
	 * Get a list of PageInfo objects representing the web pages that are
	 * associated with ANY of the specified keywords. That is, if keywords were
	 * {"cat", "dog"} then the list returned would contain all of the pages that
	 * are associated with either the keyword "cat" or the "keyword" dog. The
	 * list that is returned should be sorted into order by decreasing number of
	 * incoming links with ties broken by (case insensitive) alphabetical order
	 * of the page title. NOTE: this is the order imposed by the compareTo
	 * method in the PageInfo class.
	 * 
	 * @param keyword
	 *            the keyword for which to search.
	 * @return a sorted list of the PageInfo objects associated with any of the
	 *         keyword.
	 */
	public ArrayList<PageInfo> searchAny(String[] keywords) {
		ArrayList<PageInfo> masterList = getMasterList(keywords);

		for (String keyword : keywords) {
			ArrayList<PageInfo> pageInfoForCurKW = this.search(keyword);
			for (PageInfo pi : pageInfoForCurKW) {
				if(!masterList.contains(pi)) {
					masterList.add(pi);
				}// end if statement
			}//end for each loop
		}// end for loop with counter

		Collections.sort(masterList);

		return masterList;
	}

	/*
	 * This helper method gets the PageInfo for the first KeyWord and making it the master list of PageInfo 
	 * so that from the second keyword forward it has something to compare to
	 */
	private ArrayList<PageInfo> getMasterList(String[] keywords) {
		this.sort();
		KeyWord firstKeyWord = new KeyWord(keywords[0]);
		int indexOfFirstKW = Collections.binarySearch(keyWordList, firstKeyWord);
		ArrayList<PageInfo> masterList = keyWordList.get(indexOfFirstKW).getPages();

		return masterList;
	}
}
