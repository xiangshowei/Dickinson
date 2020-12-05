package lab08.search;

/**
 * This class holds the information about a web page that is relevant
 * to presenting the search results.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Apr 13, 2010
 */
public class PageInfo implements Comparable<PageInfo> {
	
	private String title;
	private String url;
	private int linkCount;

    
    /**
     * Construct a new PageInfo for the web page with the specified information.
     * 
     * @param title the page title.
     * @param URL the URL of the page.
     * @param linkCount the number of incoming links to the page.
     */
    public PageInfo(String title, String URL, int linkCount) {
    	this.title = title;
    	url = URL;
    	this.linkCount = linkCount;
    }

    /**
     * Get the title of the page.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the URL of the page.
     * 
     * @return the URL
     */
    public String getURL() {
        return url;
    }

    /**
     * Get the number of incoming links to the page.
     * 
     * @return the linkCount
     */
    public int getLinkCount() {
        return linkCount;
    }
    
    /**
     * Determine if this PageInfo represents the same web page as Object o.  
     * Two PageInfo objects represent the same web page if they have the same URL.
     * 
     * @param o an object to be compared to this PageInfo.
     * @return true if this PageInfo and o is a PageInfo representing the same web page
     * and false if either o is not a PageInfo or they do not represent the same web page.
     */
    public boolean equals(Object o) {
    	if(url.equals(((PageInfo)o).getURL())){
    		return true;
    	} // end if statement
        return false;
    }
    
    /**
     * This method compared one PageInfo object to another PageInfo Object.
     * Pages are ordered such that pages with higher link counts come before those with smaller link counts. 
     * In the case of ties, the tied pages are ordered alphabetically by title(case insensitive).
     */
	@Override
	public int compareTo(PageInfo o) {
		if(this.getLinkCount() < o.getLinkCount()) {
			return 1;
		}// end if statement
		else if(this.getLinkCount() > o.getLinkCount()) {
			return -1;
		}//end else if
		else {
			return this.getTitle().compareToIgnoreCase(o.getTitle());
		}// else
	}
}
