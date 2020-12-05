package lab08.search.webcrawler;

import java.io.*;
import java.util.*;

/**
 * A WebPageList is a collection of WebPageInfo objects.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 27, 2009
 */
public class WebPageList {

    private ArrayList<WebPageInfo> pages;
    
    /**
     * Construct a new empty WebPageList.
     */
    public WebPageList() {
        pages = new ArrayList<WebPageInfo>();
    }

    /**
     * Get the number of WebPageInfo objects that are currently in the list.
     * 
     * @return the size of the list.
     */
    public int getNumPages() {
        return  pages.size();
    }
    
    /**
     * Add the information in the specified WebPageInfo object to this
     * WebPageList. The WebPageInfo list will only ever contain one WebPageInfo
     * object per URL. So, if the URL in page does not yet exist in the list,
     * page is added to the list. If the exact WebPageInfo object (determined using ==)
     * exists in the list, the list is unchanged.  If a distinct WebPageInfo object for
     * the same URL already exists in the list (determined using .equals), then the 
     * information from page is added to the WebPageInfo that is already in the list. New
     * search terms are appended to the list of search terms and the link counts are added
     * together.
     * 
     * @param page A WebPageInfo object containing the information about the
     *            page to be added.
     */
    public void addPage(WebPageInfo page) {
        WebPageInfo curPage = getPage(page.getURL());
        if (curPage == null) {
            pages.add(page);
        }
        else {
            for (String term : page.getSearchTerms()) {
                curPage.addSearchTerm(term);
            }

            curPage.setLinkCount(curPage.getLinkCount() + page.getLinkCount());
        }
    }

    /**
     * Get the WebPageInfo object associated with the specified URL.
     * 
     * @param URL the URL of the page.
     * @return the WebPageInfo object for the page with the specified URL or
     *         null if there is no WebPageInfo object for that page in the list.
     */
    public WebPageInfo getPage(String URL) {
        WebPageInfo findMe = new WebPageInfo("", URL);
        int index = pages.indexOf(findMe);
        if (index == -1) {
            return null;
        }
        else {
            return pages.get(index);
        }
    }

    /**
     * Remove and return the first WebPageInfo object in the list.
     * 
     * @return the first WebPageInfo object in the list.
     * @throws IllegalStateException if the list is empty.
     */
    public WebPageInfo removeFirstPage() {
        if (pages.size() > 0) {
            return pages.remove(0);
        }
        else {
            throw new IllegalStateException("List is empty");
        }
    }

    /**
     * Remove the specified web page info object.
     * 
     * @param page the page to remove.
     */
    public void removePage(WebPageInfo page) {
        pages.remove(page);
    }
    
    /**
     * Write the contents of this WebPageList to a text file with the specified
     * name. The information about each page in the list should be written to
     * the file. The format of the file will be as follows: The URL of the page
     * appears on a line by itself, the following line contains each of the
     * search terms separated by commas (with no spaces and no comma following the
     * final term), a third line contains the number of
     * incoming links to the page that were found.  If there are no search terms 
     * associated with page then the search term line will be blank.
     * 
     * <code><xmp> 
     * Page Title
     * Page URL 
     * Term1,Term2,...,TermN 
     * Incoming link count 
     * Page Title
     * Page URL 
     * Term1,Term2,...,TermN 
     * Incoming link count 
     * ... 
     * </xmp></code>
     * 
     * @param filename the name of the file to which to write the contents of
     *            this WebPageList.
     * @throws FileNotFoundException if the specified file cannot be opened.
     */
    public void writeToFile(String filename) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(filename));

        for (WebPageInfo wpi : pages) {
            pw.println(wpi.getTitle());
            pw.println(wpi.getURL());
            
            ArrayList<String> terms = wpi.getSearchTerms();
            for (int i=0; i<terms.size()-1; i++) {
                pw.print(terms.get(i) + ",");
            }
            if (terms.size() > 0) {
                pw.println(terms.get(terms.size()-1));
            }
            else {
                pw.println();
            }
            
            pw.println(wpi.getLinkCount());
        }
        
        pw.close(); 
    }
}
