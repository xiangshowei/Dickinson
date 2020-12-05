package lab08.search.webcrawler;

import java.util.ArrayList;

/**
 * Class representing a Link from a web page.  The class is a wrapper
 * for the link URL and the text that makes up the link.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 6, 2009
 */
public class Link {
    private String url;
    private String linkText;
    private ArrayList<String> linkWords;
    
    /**
     * Construct a new Link.  
     * 
     * @param url the URL that is the target of the link.
     * @param text the text of the link.
     */
    public Link(String url, String text) {
        this.url = url;
        linkText = text;
        
        linkWords = new ArrayList<String>();
        String[] linkWordsArr = linkText.split("[ \n\r\t]");
        for (String word : linkWordsArr) {
            String trimmed = word.trim();
            if (trimmed.length() > 0) {
                linkWords.add(trimmed);
            }
        }
    }
    
    /**
     * Get the URL.
     * @return get the target of the link.
     */
    public String getURL() {
        return url;
    }
    
    /**
     * Get the text of the link.
     * @return the link's text.
     */
    public String getText() {
        return linkText;
    }
    
    /**
     * Get an ArrayList of Strings containing each of the individual
     * words in the link text.
     * 
     * @return An ArrayList of Strings containing the words in the link text.
     */
    public ArrayList<String> getLinkWords() {
        return linkWords;
    }
}
