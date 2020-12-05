package lab08.search.webcrawler;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;

import org.htmlparser.util.ParserException;
import org.osjava.norbert.NoRobotException;

/**
 * A simple web crawler for building a web index. The crawler begins at a given
 * page and builds a list of search terms for that page based on the page's
 * title and any keywords that are listed for the page. It then indexes each
 * page that is linked to from the starting page. For every linked page a list
 * of search terms are created from the link text, the page's title and
 * keywords. The resulting index is then written to a file.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 27, 2009
 */
public class WebCrawler {

    private static int MAX_PAGES;

    private String startURL;
    private WebPageList toProcess;
    private WebPageList processed;

    /**
     * Construct a new WebCrawler.
     */
    public WebCrawler(int maxPages) {
        MAX_PAGES = maxPages;
        toProcess = new WebPageList();
        processed = new WebPageList();
    }

    /**
     * Crawl the web beginning at the specified URL.
     * 
     * @param URL the starting URL for the crawl.
     */
    public void crawl(String URL) {
        startURL = URL;
        WebPageInfo startPage = new WebPageInfo("", URL + "/");
        toProcess.addPage(startPage);

        while (toProcess.getNumPages() != 0 && processed.getNumPages() < MAX_PAGES) {

            WebPageInfo curPage = toProcess.removeFirstPage();
            processed.addPage(curPage);

            try {
                HTMLTagExtractor te = new HTMLTagExtractor(curPage.getURL());
                curPage.setTitle(getTitle(te, curPage.getURL()));

                processTitle(curPage, te);
                processKeyWords(curPage, te);
                processLinks(curPage, te);
            }
            catch (MalformedURLException e) {
                processed.removePage(curPage);
                System.out.println("Skipping " + curPage.getURL() + " bad URL format.");
            }
            catch (ParserException e) {
                processed.removePage(curPage);
                System.out.println("Skipping " + curPage.getURL() + " unable to parse page.");
            }
            catch (NoRobotException e) {
                processed.removePage(curPage);
                System.out.println("Skipping " + curPage.getURL() + " robots are disallowed.");
            }
        }

        try {
            processed.writeToFile(URL.substring(URL.indexOf(":") + 3) + ".index");
        }
        catch (FileNotFoundException e) {
            System.out.println("Unable to write output file.");
        }
    }

    /*
     * Get the title of the web page as a string.
     */
    private String getTitle(HTMLTagExtractor te, String URL) throws ParserException {
        ArrayList<String> titleList = te.getTitle();
        String title = "";

        for (String titleWord : titleList) {
            if (title.length() == 0) {
                title = titleWord;
            }
            else {
                title = title + " " + titleWord;
            }
        }

        /*
         * If the parser can't find a title - hack one from the URL.
         */
        if (title.length() == 0) {
            if (URL.endsWith("/")) {
                URL = URL.substring(0, URL.length() - 1);
            }

            int slash = URL.lastIndexOf("/");
            if (slash >= 0) {
                title = URL.substring(slash + 1);
            }
            else {
                title = URL;
            }
        }

        return title;
    }

    /*
     * Handle the processing of the Title of the current page. Each word in the
     * title is added to the current page as a search term.
     */
    private void processTitle(WebPageInfo curPage, HTMLTagExtractor te) throws ParserException {
        ArrayList<String> title = te.getTitle();
        for (String titleWord : title) {
            curPage.addSearchTerm(titleWord);
        }
    }

    /*
     * Handle the processing of the Keywords of the current page. Each keyword
     * of the current page is added to the current page as a search term.
     */
    private void processKeyWords(WebPageInfo curPage, HTMLTagExtractor te) throws ParserException {
        ArrayList<String> keyWords = te.getKeyWords();
        for (String word : keyWords) {
            curPage.addSearchTerm(word);
        }
    }

    /*
     * Handle the processing of the Links on the current page. Each link on the
     * current page represents a new page to be processed. If the linked page is
     * not already queued to be processed and has not already been processed
     * then, each word in the link text is added to the new page as a search
     * term. The new page is then added to the toProcess list so that it will be
     * processed in turn.
     */
    private void processLinks(WebPageInfo curPage, HTMLTagExtractor te) throws ParserException {
        ArrayList<Link> links = te.getLinks();
        for (Link link : links) {
            WebPageInfo procPage = processed.getPage(link.getURL());
            ArrayList<String> linkWords = link.getLinkWords();

            if (procPage != null) {
                // already processed the page the link points to.
                procPage.incrementLinkCount();
                for (String word : linkWords) {
                    procPage.addSearchTerm(word);
                }
            }
            else {
                // have not already processed the page the link points to
                WebPageInfo toProcPage = toProcess.getPage(link.getURL());

                if (toProcPage != null) {
                    // haven't processed it yet but it is queued for processing
                    toProcPage.incrementLinkCount();
                    for (String word : linkWords) {
                        toProcPage.addSearchTerm(word);
                    }
                }
                else {
                    // haven't processed it yet and it is not yet queued for processing
                    if (link.getURL().contains(startURL)) {
                        // only process sub-pages of given url.

                        if (link.getURL().startsWith("http://")) {
                            // Skip anything that is not plain http (e.g. ftp / https)
                            WebPageInfo linkedPage = new WebPageInfo("", link.getURL());
                            linkedPage.incrementLinkCount();
                            for (String word : linkWords) {
                                linkedPage.addSearchTerm(word);
                            }

                            toProcess.addPage(linkedPage);
                        }
                    }
                }
            }
        }
    }

    /**
     * Main method that creates a WebCrawler and sets it to crawling.
     * 
     * @param args the URL at which to start crawling.
     */
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        System.out.println("This program generates an index file for all pages under");
        System.out.println("a given starting URL.");
        System.out.println();
        System.out.print("Enter starting URL (e.g. www.dickinson.edu): ");
        String url = scr.nextLine();
        System.out.print("Enter the maximum number of pages to index: ");
        String numStr = scr.nextLine();
        int num = Integer.parseInt(numStr);

        WebCrawler wc = new WebCrawler(num);
        wc.crawl("http://" + url);
    }
}
