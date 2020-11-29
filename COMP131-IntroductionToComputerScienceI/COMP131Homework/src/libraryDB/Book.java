package libraryDB;

/**
 * A class to represent books in a library. Books have a title, author, call
 * number, a subject, a publication year, a number of pages and an indication of
 * whether or not they are currently checked out.
 * 
 * SUBJECTM,SUBJECTC,SUBJECTH,SUBJECTF,OLD_BOOK_YEAR public static final int
 * OLD_BOOK_YEAR = 1900; public static final String SUBJECTM = "Math"; public
 * static final String SUBJECTC = "Computer Science"; public static final String
 * SUBJECTH = "History"; public static final String SUBJECTF = "Fly Fishing";
 * 
 * @author Tim Wahls
 * @author Xiang Wei
 * @version (PUT DATE HERE)
 */
public class Book {
    private String title;
    private String author;
    private String callNumber;
    private String subject;
    private int pubYear;
    private int numPages;
    private boolean checkedOut;

    public static final int OLD_BOOK_YEAR = 1900;
    public static final String SUBJECT_MATH = "Math";
    public static final String SUBJECT_CS = "Computer Science";
    public static final String SUBJECT_HISTORY = "History";
    public static final String SUBJECT_FLY_FISHING = "Fly Fishing";

    /**
     * Create a new book. A new book is not initially checked out.
     * 
     * @param initTitle   the title
     * @param initAuthor  the author
     * @param initCall    the call number
     * @param initSubject the subject
     * @param initYear    the publication year
     * @param initPages   the number of pages
     */
    public Book(String initTitle, String initAuthor, String initCall, String initSubject, int initYear, int initPages) {
        title = initTitle;
        author = initAuthor;
        callNumber = initCall;
        
        if (initSubject.equals("Math")) {
            subject = SUBJECT_MATH;
        } 
        else if (initSubject.equals("Computer Science")) {
            subject = SUBJECT_CS;
        } 
        else if (initSubject.equals("History")) {
            subject = SUBJECT_HISTORY;
        } 
        else {
            subject = SUBJECT_FLY_FISHING;
        }

        pubYear = initYear;
        numPages = initPages;
        checkedOut = false;
    }

    /**
     * Returns the title
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author 
     * 
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the call number
     * 
     * @return the call number
     */
    public String getCallNumber() {
        return callNumber;
    }

    /**
     * Return the subject
     * 
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Return the publication year
     * 
     * @return the publication year
     */
    public int getPublicationYear() {
        return pubYear;
    }

    /**
     * Return the number of pages
     * 
     * @return the number of pages
     */
    public int getNumPages() {
        return numPages;
    }

    /**
     * Check to see if the book is currently checked out
     * 
     * @return whether or not the book is currently checked out
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }

    /**
     * check if the book is older than a certain year
     * 
     * @return true if it is otherwise false
     */
    public boolean isOld() {
        return pubYear < OLD_BOOK_YEAR;
    }

    /**
     * Mark the book as checked out. Display an error message if the book is already checked out.
     */
    public void markAsCheckedOut() {
        if (checkedOut) {
            System.out.println("Error: " + title + " is already checked out!");
        } 
        else {
            checkedOut = true;
        }
    }

    /**
     * Mark the book as available for checkout. Display an error message if the book
     * is not already checked out.
     */
    public void markAsAvaiable() {
        if (!checkedOut) {
            System.out.println("Error: " + title + " is not checked out!");
        } 
        else {
            checkedOut = false;
        }
    }

    /**
     * Print all information about this book, including whether or not it is checked out.
     */
    public void printBookInformation() {
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
}
