package lab08.search;

import java.io.*;
import java.util.*;

/**
 * A program that uses the KeyWord, KeyWordList and PageInfo implementations to
 * perform keyword searches of the web pages that have been indexed.
 * 
 * @author Grant Braught
 * @version 7 April 2015
 */
public class SearchEngine {

	/**
	 * The main SearchEngine program.
	 * 
	 * @param args none
	 */
	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);

		KeyWordList pageIndex = null;
		while (pageIndex == null) {

			// Get the index generated by the webcrawler...
			System.out.print("What index file do you want to use: ");
			String indexFile = scr.nextLine();
			try {
				pageIndex = new KeyWordList(indexFile);
			} catch (FileNotFoundException e) {
				System.out.println("Unable to find file: " + indexFile);
				System.out.println("Please try again.");
			}
		}

		// Keep going until the user quits.
		String searchTerms = "";
		while (!searchTerms.toUpperCase().equals("Q")) {
			System.out.print("Enter search term(s) [Q to quit]: ");
			searchTerms = scr.nextLine();

			if (searchTerms.equals("")) {
				System.out.println("No search terms entered. Please try again.");
			}
			// If the user did not quit then do the search.
			else if (!searchTerms.toUpperCase().equals("Q")) {
				String[] searchTermsArray = searchTerms.split(" ");

				String yn = "";
				if (searchTermsArray.length > 1) {
					// Determine if ANY or ALL search terms are to be included.
					while (!(yn.equals("Y") || yn.equals("N"))) {
						System.out.print("Require all terms [y/n]: ");
						yn = scr.nextLine().toUpperCase();
						if (!(yn.equals("Y") || yn.equals("N"))) {
							System.out.println("Please enter y or n.");
						}
					}
				}

				// Do the search...
				ArrayList<PageInfo> searchResults = null;
				if (yn.equals("Y")) {
					searchResults = pageIndex.searchAll(searchTermsArray);
				} else { // "N"
					searchResults = pageIndex.searchAny(searchTermsArray);
				}

				// Display the results...
				System.out.println();
				System.out.println("Results:");
				if (searchResults != null && searchResults.size() == 0) {
					System.out.println("No matches found.");
				} else {
					for (PageInfo pi : searchResults) {
						System.out.println(pi.getTitle());
						System.out.println("\t" + pi.getURL());
					}
				}
				System.out.println();
			}
		}

		scr.close();
	}
}
