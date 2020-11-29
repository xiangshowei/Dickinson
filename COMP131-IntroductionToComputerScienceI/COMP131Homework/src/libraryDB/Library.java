package libraryDB;

import java.util.Set;
import java.util.HashMap;
/**
 * A class to keep track of a library of books.
 * 
 * @author Tim Wahls 
 * @author Xiang Wei
 * @version 11/20/2013
 */
public class Library {
    private String libraryName;
    private HashMap<String, Book> books;
    
    /**
     * Create a new Library with the specified name and no books.
     * Each book is uniquely identified via its call number.
     * 
     * @param initName name of the library
     */
    public Library(String initName) {  
        libraryName = initName;
        books = new HashMap<String, Book>();
    }

    /**
     * Return the name of the library
     * 
     * @return the name of the library
     */
    public String getLibraryName() {
        return libraryName;
    }

    /**
     * Return the number of books in the library
     * 
     * @return the number of books in the library
     */
    public int getNumBooks() {
        return books.size() ;
    }
    
    /**
     * Get a book by call number
     * 
     * @param callNum the call number
     * @return the book with the specified call number, or null if no book
     * in the library has that call number
     */
    public Book getBook(String callNum) {
        Book book = books.get(callNum);
        return book;
    }

    /**
     * Add a book to the library
     * 
     * @param newBook the book to add
     */
    public void addBook(Book newBook) {
        books.put(newBook.getCallNumber(), newBook);
    }

    /**
     * Remove a book by the specified author from the library's collection, and
     * display an error message if the library has no books by that author
     * 
     * @param author the author's name
     */
    public void removeBookByAuthor(String author) {
        Book bookByAuthor = getBookByAuthor(author);

        if (bookByAuthor != null) {
            books.remove(bookByAuthor.getCallNumber());
        } 
        else {
            System.out.println("Error: the library has no books by " + author);
        }
    }    

    /**
     * Determine whether or not the library has a book by the specified author
     * 
     * @param author the author's name
     * @return whether the library has a book by the specified author
     */
    public boolean hasBookByAuthor(String author) {
        return getBookByAuthor(author) != null;
    }


    /**
     * Check out the specified book, displaying an error message if the library does
     * not have the book with the specified call number, or if the book is already checked out.
     * 
     * @param callNum the call number of the book to check out
     */
    public void checkOutBook(String callNum) {
        if (getBook(callNum) == null) {
            System.out.println("no such book or the book is checked out");
        }

        else {
            books.get(callNum).markAsCheckedOut();
        }

    }

    /**
     * Return the specified book, 
     * displaying an error message if the library does
     * not have the book with the specified call number, or if the book is not
     * checked out.
     * 
     * @param callNum the call number of the book to check out
     */
    public void returnBook(String callNum) {
        if (getBook(callNum) == null) {
            System.out.println("no such book or the book is not checked out");
        }
        else {
            books.get(callNum).markAsAvaiable();
        }   
    }

    /**
     * Display information about the library
     */
    public void printLibraryInformation() {
        System.out.println(libraryName);

        Set<String> allCallNumbers = books.keySet();
        for (String callNumber : allCallNumbers) {
            books.get(callNumber).printBookInformation();
        }
    }
    
    
    /**
     * count the number of the books by subject
     */
    public void countBooksBySubject() {
        int numMathBooks = 0;
        int numCSBooks = 0;
        int numHistoryBooks = 0;
        int numFlyFishingBooks = 0;

        Set<String> allCallNumbers = books.keySet();
        for (String callNumber : allCallNumbers) {
            String subject = books.get(callNumber).getSubject();
            if (subject.equals(Book.SUBJECT_MATH)) {
                numMathBooks++;
            }
            else if (subject.equals(Book.SUBJECT_CS)) {
                numCSBooks++;
            }
            else if (subject.equals(Book.SUBJECT_HISTORY)) {
                numHistoryBooks++;
            }
            else {
                numFlyFishingBooks++;
            }
        }
        System.out.println("Math:" + numMathBooks);
        System.out.println("History:" + numHistoryBooks);
        System.out.println("Computer Science:" + numCSBooks);
        System.out.println("Fly Fishing:" + numFlyFishingBooks);
    }

    /**
     * Get the call number by a particular author
     * 
     * @param authorName the name of the author
     * @return callNumber by authorName
     */
    private Book getBookByAuthor(String authorName) {
        Book bookByAuthor = null;
        
        Set<String> allCallNumbers = books.keySet();
        for (String callNumber: allCallNumbers) {
            Book book = books.get(callNumber);
           
            if (authorName.equals(book.getAuthor())) {
                bookByAuthor = book;
                return bookByAuthor;
            }
        }
    
        return bookByAuthor;
    }
}
