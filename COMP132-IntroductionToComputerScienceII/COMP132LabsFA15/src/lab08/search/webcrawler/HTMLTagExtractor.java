package lab08.search.webcrawler;

import java.net.*;
import java.util.*;

import org.htmlparser.*;
import org.htmlparser.tags.*;
import org.htmlparser.util.*;
import org.htmlparser.visitors.*;
import org.osjava.norbert.*;

/**
 * Provides direct access to a few types of tags from within an HTML page, and
 * easy access to all of the tags within a page. This class is a wrapper around
 * some of the functionality provided by the open source Java HTMLParser package
 * (http://htmlparser.sourceforge.net/).
 * 
 * <p>
 * The HTMLTagExtractor will honor the restrictions noted in a site's robots.txt
 * file. If an HTMLTagExtractor is constructed for a URL that is listed with a
 * Disallow: in the site's robots.txt file, the constructor will throw a
 * NoRobotException.
 * 
 * <p>
 * The HTMLTagExtractor will fetch at most 2 pages per second. This ensures that
 * a web crawler created using this class will "play nice" and not overwhelm web
 * servers with a barrage of requests. Normally, one might instead spread
 * requests across domains so that pages could still be fetched more rapidly
 * while still "playing nice." That is overly complicated for the assignment
 * where this class is to be used, so the simpler method is being used.
 * 
 * <p>
 * The HTMLTagExtractor will fetch at most 100 pages. If the 101st page is
 * attempted the constructor for this class will terminate the program. This
 * ensures that errant client programs do not fetch excessive numbers of pages
 * from a target site.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 5, 2009
 */
public class HTMLTagExtractor {

    private URL url;
    private Parser parser;
    private NodeList nodes;

    private static final int MAX_PAGES = 200;
    private static int totalPages = 0;

    /*
     * Make this static and track which site the robots.txt was last parsed for.
     * Since most refs will be to the same site as the previous one this will
     * prevent us from fetching the same robots.txt file over and over again.
     */
    private static NoRobotClient nrc = null;;
    private static URL nrcBase = null;

    /**
     * Create an HTMLTagExtractor for the specified URL. The specified URL must
     * include the protocol prefix (e.g. http://).
     * 
     * @param pageURL the URL of the page to parse.
     * @throws MalformedURLException if the pageURL string does not specify a
     *             valid URL.
     * @throws ParserException if the page indicated by pageURL cannot be
     *             parsed.
     * @throws NoRobotException if the site's robots.txt file indicates that
     *             robots are not allowed to process the pageURL.
     */
    public HTMLTagExtractor(String pageURL) throws MalformedURLException, ParserException,
            NoRobotException {

        if (totalPages < MAX_PAGES) {
            /*
             * Ensure that at most 2 pages per second can be fetched by
             * HTMLTagExtractor objects.
             */
            synchronized (this) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    System.out.println("HTMLTagExtractor may not be playing nice with servers.");
                    System.out.println("Terminating!");
                    System.exit(-1);
                }

                System.out.println("Parsing page: " + pageURL);

                url = new URL(pageURL);

                if (!robotOK(url)) {
                    throw new NoRobotException("Robots not allowed on: " + pageURL);
                }

                parser = new Parser(pageURL);
                nodes = parser.parse(null);
            }

            totalPages++;
        }
        else {
            System.out.println("Maximum number of pages has been exceeded.");
            System.out.println("Terminating!");
            System.exit(-1);
        }
    }

    /*
     * Check to see if it the site allows robots for the specified url.
     */
    private boolean robotOK(URL url) throws MalformedURLException, NoRobotException {
        if (url.getProtocol().startsWith("file")) {
            return true;
        }
        else {
            URL base = new URL("http://" + url.getHost() + "/");

            /*
             * Only fetch the robots.txt if this site is different from the one
             * from which we most recently fetched robots.txt.
             */
            if (nrcBase == null || !base.getHost().equals(nrcBase.getHost())) {
                System.out.println("Fetching robots.txt from " + base);
                nrcBase = base;
                nrc = new NoRobotClient("COMP132 Crawler");
                nrc.parse(base);
            }

            return nrc.isUrlAllowed(url);
        }
    }

    /**
     * Get a String containing the URL that this HTMLTagExtractor is assigned to
     * parse.
     * 
     * @return the URL for this HTMLParser.
     */
    public String getURL() {
        return url.toExternalForm();
    }

    /**
     * Get an ArrayList containing all of the keywords listed in the META tags
     * for the page.
     * 
     * @return an ArrayList of Strings holding the keywords specified in the
     *         page's META tags. If there are no keywords specified for the page
     *         then this method returns an empty ArrayList.
     * 
     * @throws ParserException if an error occurs when attempting to parse the
     *             page or the META tags.
     */
    public ArrayList<String> getKeyWords() throws ParserException {
        KeywordVisitor kwv = new KeywordVisitor();
        nodes.visitAllNodesWith(kwv);
        return kwv.getKeywords();
    }

    /**
     * Get the words contained in the TITLE tag of the page.
     * 
     * @return An ArrayList of Strings containing each word in the title of the
     *         page. If no TITLE tag exists then this method returns an empty
     *         ArrayList.
     * 
     * @throws ParserException if an error occurs when attempting to parse the
     *             page or the TITLE tag.
     */
    public ArrayList<String> getTitle() throws ParserException {
        TitleVisitor tv = new TitleVisitor();
        nodes.visitAllNodesWith(tv);
        return tv.getTitleWords();
    }

    /**
     * Get an ArrayList of Link objects containing one Link object for each link
     * that appears on the page. Each Link object will contain the URL of the
     * link and also the text (if any) that is displayed for the link.
     * 
     * @return An ArrayList of Link objects representing the links on the page.
     *         If the page does not contain any links then an empty ArrayList
     *         will be returned.
     * 
     * @throws ParserException if an error occurs when attempting to parse the
     *             the page or one of the links (i.e. A tags).
     */
    public ArrayList<Link> getLinks() throws ParserException {
        LinkVisitor lv = new LinkVisitor();
        nodes.visitAllNodesWith(lv);
        return lv.getLinks();
    }

    /**
     * Get an ArrayList containing all of the tags of a specified type from the
     * page. The type of the tag is specified using one of the class names from
     * the org.htmlparser.tags package from the HTMLParser project. The
     * documentation for org.htmlparser.tags can be found on the HTMLParser
     * project homepage at:
     * 
     * http://htmlparser.sourceforge.net/
     * 
     * @param tagType the name of the class corresponding to the type of the tag
     *            that is desired.
     * 
     * @return An ArrayList of TagNode objects, one for each tag of the
     *         specified type in the page. The TagNode class is also defined in
     *         by the HTMLParser project. See the documentation for that project
     *         for more information on how to work with TagNode objects.
     * 
     * @throws ParserException if an error occurs when attempting to parse the
     *             the page or one of the tags.
     */
    public ArrayList<Tag> getTags(String tagType) throws ParserException {
        TagMatchVisitor tmv = new TagMatchVisitor(tagType);
        nodes.visitAllNodesWith(tmv);
        return tmv.getTags();
    }

    /**
     * Get the NodeList as produced by the HTMLParser. The NodeList contains
     * Nodes corresponding to all of the HTML Tags in the page. The
     * documentation for NodeList class can be found on the HTMLParser project
     * homepage at:
     * 
     * http://htmlparser.sourceforge.net/
     * 
     * @return the NodeList generated by the HTMLParser.
     */
    public NodeList getAllTags() {
        return nodes;
    }

    /*
     * NodeVisitor that produces a list of tags whose class name matches a given
     * string.
     */
    private class TagMatchVisitor extends NodeVisitor {
        private ArrayList<Tag> tags;
        private String tagType;

        public TagMatchVisitor(String tagType) {
            tags = new ArrayList<Tag>();
            this.tagType = tagType;
        }

        public void visitTag(Tag tag) {
            if (tag.getClass().getName().endsWith(tagType)) {
                tags.add(tag);
            }
        }

        public ArrayList<Tag> getTags() {
            return tags;
        }
    }

    /*
     * Visitor that makes available one Link object for each LINK tag in the
     * page being parsed.
     */
    private class LinkVisitor extends NodeVisitor {
        private ArrayList<Link> links;

        public LinkVisitor() {
            links = new ArrayList<Link>();
        }

        public void visitTag(Tag tag) {
            if (tag instanceof LinkTag) {
                LinkTag l = (LinkTag) tag;

                String linkURL = l.getLink();
                String linkText = l.getLinkText();

                Link link = new Link(linkURL, linkText);
                links.add(link);
            }
        }

        public ArrayList<Link> getLinks() {
            return links;
        }
    }

    /*
     * Visitor that makes available the title specified in the TITLE tag of the
     * page.
     */
    private class TitleVisitor extends NodeVisitor {
        private String title;

        public TitleVisitor() {
            title = "";
        }

        public void visitTag(Tag tag) {
            if (tag instanceof TitleTag) {
                TitleTag t = (TitleTag) tag;
                title = t.getTitle();
            }
        }

        public ArrayList<String> getTitleWords() {
            ArrayList<String> words = new ArrayList<String>();

            String[] titleArr = title.split("[ \n\r\t]");
            for (String titleWord : titleArr) {
                String trimmed = titleWord.trim();
                if (trimmed.length() > 0) {
                    words.add(trimmed);
                }
            }

            return words;
        }
    }

    /*
     * Visitor that makes available all of the keywords specified in the META
     * tags of the page.
     */
    private class KeywordVisitor extends NodeVisitor {
        private ArrayList<String> keywords;

        public KeywordVisitor() {
            keywords = new ArrayList<String>();
        }

        public void visitTag(Tag tag) {
            if (tag instanceof MetaTag) {
                MetaTag m = (MetaTag) tag;
                String tagName = m.getMetaTagName();
                if (tagName != null && tagName.equalsIgnoreCase("Keywords")) {

                    String keywordString = m.getMetaContent();
                    String[] words = keywordString.split("[, \n\r\t]");

                    for (String w : words) {
                        String trimmed = w.trim();
                        if (trimmed.length() > 0) {
                            keywords.add(trimmed);
                        }
                    }
                }
            }
        }

        public ArrayList<String> getKeywords() {
            return keywords;
        }
    }
}
