package lab08.search;

import java.util.ArrayList;

/**
 * This class represents a keyword and a set of associated web pages.  The 
 * associated web pages are those pages that should be returned when this
 * keyword is searched for.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Apr 13, 2010
 */
public class KeyWord implements Comparable<KeyWord>{

	private String word;
	private ArrayList<PageInfo> keywords;

	/**
	 * Construct a new KeyWord for the specified word.  Initially there are
	 * no web pages associated with the keyword.
	 * 
	 * @param word the keyword.
	 */
	public KeyWord(String word) {
		this.word = word;
		keywords = new ArrayList<PageInfo>();
	}

	/**
	 * Get the keyword.
	 * 
	 * @return the keyword.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Add a web page to the list of pages associated with this keyword. Web
	 * pages should only be able to be added once.  If a page is added more than
	 * once it will still only appear in the list once.
	 * 
	 * @param page the page to be added.
	 */
	public void addPage(PageInfo page) {
		if(!keywords.contains(page)){
			keywords.add(page);  
		}// end if statement
	}

	/**
	 * Get the list of web pages associated with this keyword.  If there are no pages
	 * associated with the keyword then this method returns an empty ArrayList.
	 * 
	 * @return the web pages associated with this keyword.
	 */
	public ArrayList<PageInfo> getPages() {
		return keywords;
	}
	
	/**
	 * Checks to see if the this KeyWord object is the same as the other KeyWord object. 
	 * @param kw the KeyWord that is being compared to
	 * @return true if both keywords are the same, false otherwise
	 */
	public boolean equals(Object o) {
		if(word.equals(((KeyWord)o).getWord())){
			return true;
		}// end if statement
		return false;
	}

	/**
	 * This method compares KeyWord objects by the String that the 
	 * KeyWord object was created with.
	 */
	@Override
	public int compareTo(KeyWord o) {
		return this.getWord().compareToIgnoreCase(o.getWord());
	}
}
