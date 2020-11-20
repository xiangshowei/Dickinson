package libraryDB;

/**
 * A class to represent books in a library.  Books have a title, author, call
 * number, a subject, a publication year, a number of pages and an indication of
 * whether or not they are currently checked out.
 * SUBJECTM,SUBJECTC,SUBJECTH,SUBJECTF,OLD_BOOK_YEAR
 * public static final int OLD_BOOK_YEAR = 1900;
 * public static final String SUBJECTM = "Math";
 * public static final String SUBJECTC = "Computer Science";
 * public static final String SUBJECTH = "History";
 * public static final String SUBJECTF = "Fly Fishing";
 * @author Tim Wahls 
 * @author (YOUR NAME HERE) 
 * @version (PUT DATE HERE)
 */
public class Book
{
    private String title;
    private String author;
    private String callNumber;
    private String subject;
    private int pubYear;
    private int numPages;
    private boolean checkedOut;
    /**
     * public static final int OLD_BOOK_YEAR
     */
    public static final int OLD_BOOK_YEAR = 1900;
    /**
     * public static final String SUBJECTM
     */
    public static final String SUBJECTM = "Math";
    /**
     * public static final String SUBJECTC
     */
    public static final String SUBJECTC = "Computer Science";
    /**
     *  public static final String SUBJECTH
     */
    public static final String SUBJECTH = "History";
    /**
     * public static final String SUBJECTF
     */
    public static final String SUBJECTF = "Fly Fishing";
    /**
     * Create a new book.  A new book is not initially checked out.
     * 
     * @param initTitle the title
     * @param initAuthor the author
     * @param initCall the call number
     * @param initSubject the subject
     * @param initYear the publication year
     * @param initPages the number of pages
     */
    public Book(String initTitle, String initAuthor, String initCall,
    String initSubject, int initYear, int initPages)
    {
        title = initTitle;
        author = initAuthor;
        callNumber = initCall;
        if (initSubject.equals("Math")) { 
            subject = SUBJECTM;
        }
        else if (initSubject.equals("Computer Science")) {
            subject = SUBJECTC;
        }
        else if (initSubject.equals("History")) {
            subject = SUBJECTH;
        }
        else {
            subject = SUBJECTF;
        }
        pubYear = initYear;
        numPages = initPages;
        checkedOut = false;
    }

    /**
     * get the title
     * @return the title
     */
    public String getTitle()	{
        return title;
    }

    /**
     * get the author
     * @return the author
     */
    public String getAuthor()	{
        return author;
    }

    /**
     * get the call number
     * @return the call number
     */
    public String getCallNumber()	{
        return callNumber;
    }

    /**
     * get the subject
     * @return the subject
     */
    public String getSubject()	{
        return subject;
    }	

    /**
     * get the publication year
     * @return the publication year
     */
    public int getPublicationYear()	{
        return pubYear;
    }

    /**
     * get the number of pages
     * @return the number of pages
     */
    public int getNumPages()	{
        return numPages;
    }	

    /**
     * check to see if the book is currently checked out
     * @return whether or not the book is currently checked out
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }	

    /** 
     * mark the book as checked out.  Display an error message if the book is
     * already checked out.
     */
    public void checkOutBook() {
        if (checkedOut) {
            System.out.println("Error: " + title + " is already checked out!");
        } 
        else {
            checkedOut = true;
        }
    }

    /** 
     * mark the book as NOT being checked out.  
     * Display an error message if the book
     * is not already checked out.
     */
    public void returnBook() {
        if (!checkedOut) {
            System.out.println("Error: " + title + " is not checked out!");
        } 
        else {
            checkedOut = false;
        }
    }	

    /**
     * print all information about this book, including whether or not it is
     * checked out.
     */
    public void print() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Call Number: " + callNumber);
        System.out.println("Subject: " + subject);
        System.out.println("Publication year: " + pubYear);
        System.out.println("Number of pages: " + numPages);
        if (checkedOut) {
            System.out.println("currently checked out");
        } 
        else {
            System.out.println("not checked out");
        }
    }

    /**
     * check if the book is older than a certain year
     * @return true if it is 
     * otherwise false
     */
    public boolean isOld() {
        return getPublicationYear() < Book.OLD_BOOK_YEAR;
    }
}
