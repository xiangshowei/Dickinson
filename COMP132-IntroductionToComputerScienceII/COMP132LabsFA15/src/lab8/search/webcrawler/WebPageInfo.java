package lab08.search.webcrawler;

import java.util.*;

/**
 * This class holds information about a particular web page including 
 * its URL, the list of search terms associated with the page and the
 * number of incoming links 
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 26, 2009
 */
public class WebPageInfo {

    private String pageTitle;
    private String pageURL;
    private ArrayList<String> terms;
    private int linkCount;
    
    /**
     * Construct a new WebPageInfo object for the page with the 
     * specified title and URL.
     * 
     * @param title the title for the page.
     * @param pageURL the URL for the page.
     */
    public WebPageInfo(String pageTitle, String pageURL) {
        this.pageTitle = pageTitle;
        this.pageURL = pageURL;
        terms = new ArrayList<String>();
        linkCount = 0;
    }
    
    /**
     * Get the Title of the associated page.
     * 
     * @return the page's title.
     */
    public String getTitle() {
        return pageTitle;
    }
    
    /**
     * Set the title of the associated page.
     * 
     * @param newTitle the new title for the page.
     */
    public void setTitle(String newTitle) {
        pageTitle = newTitle;
    }
    
    /**
     * Get the URL of the associated page.
     * 
     * @return the page's URL.
     */
    public String getURL() {
        return pageURL;
    }
    
    /**
     * Add a new search term for this page.  If the specified term is already
     * associated with this page then it is not added again.
     * 
     * @param term the search term to be added.
     */
    public void addSearchTerm(String term) {
        if (!terms.contains(term)) {
            terms.add(term);
        }
    }
    
    /**
     * Get a complete list of the search terms for the page.
     * 
     * @return An ArrayList of Strings containing the search terms.
     */
    public ArrayList<String> getSearchTerms() {
        return terms;
    }
    
    /**
     * Increment the counter that is tracking the number of incoming links 
     * to this page that have been found.
     */
    public void incrementLinkCount() {
        linkCount++;
    }
    
    /**
     * Set the number of incoming links to this page to the specified value.
     * 
     * @param newCount the number of incoming links to this page.
     */
    public void setLinkCount(int newCount) {
        linkCount = newCount;
    }
    
    /**
     * Get the number of incoming links to this page that have been found.
     * 
     * @return the number of incoming links.
     */
    public int getLinkCount() {
        return linkCount;
    }
    
    /**
     * Determine if this WebPageInfo is holding information about the same page
     * as obj.
     * 
     * @return true if the URL of this WebPageInfo is the same as the WebPageInfo
     * referred to by obj and false otherwise.  If obj does not refer to a WebPageInfo
     * object then this method returns false.
     */
    public boolean equals(Object obj) {
        if (obj instanceof WebPageInfo) {
            WebPageInfo wpi = (WebPageInfo) obj;
            return wpi.getURL().equals(pageURL);
        }
        else {
            return false;
        }
    }
}
