package libraryDB;

import java.util.Set;
import java.util.HashMap;
/**
 * A class to keep track of a library of books.
 * 
 * @author Tim Wahls 
 * @author ( Mingshu Zong)
 * @version (2013/11/20)
 */
public class Library
{
    private String libName;
    private HashMap<String, Book> books;
    /**
     * Create a new Library with the specified name and no books
     * 
     * @param initName name of the library
     */
    public Library(String initName)
    {  
        libName = initName;
        books = new HashMap<String, Book>();
    }

    /**
     * get the name of the library
     * 
     * @return the name of the library
     */
    public String getLibraryName()
    {
        return libName;
    }

    /**
     * get the number of books in the library
     * 
     * @return the number of books in the library
     */
    public int getNumBooks() {
        return books.size() ;
    }

    /**
     * add a book to the library
     * 
     * @param newBook the book to add
     */
    public void addBook(Book newBook) {
        books.put(newBook.getCallNumber(), newBook);
    }

    /**
     * get a book by call number
     * 
     * @param callNum the call number
     * @return the book with the specified call number, or null if no book
     * in the library has that call number
     */
    public Book getBook(String callNum) {
        Book abook = books.get(callNum);
        return abook;
    }

    /**
     * determine whether or not the library has a book by the specified author
     * 
     * @param author the author's name
     * @return true if the library has 
     * a book by the specified author, false o.w.
     */
    public boolean hasBookByAuthor(String author) {
        //         return getPositionOfBook(author) != -1;
        return getSpecificCallNum(author) != "not found";
    }

    /**
     * remove a book by the specified author from the library's collection, and
     * display an error message if the library has no books by that author
     * 
     * @param author the author's name
     */
    public void deleteBookByAuthor(String author) {
        boolean found = getSpecificCallNum(author) != "not found"; 
        if (found) {
            //             books.remove(getPositionOfBook(author));
            books.remove(getSpecificCallNum(author));
        } 
        else {
            System.out.println("Error: the library has no books by " + author);
        }
    }    

    /**
     * check out the specified book,
     * displaying an error message if the library does
     * not have the book with the specified call number,
     * or if the book is already
     * checked out.
     * 
     * @param callNum the call number of the book to check out
     */
    public void checkOutBook(String callNum) {
        if ( getBook(callNum) == null) {
            System.out.println("there is no this book");
        }
        else {
            //                 if (books.get(index).
            //             getCallNumber().equals(callNum)) {
            books.get(callNum).checkOutBook();
        }

    }

    /**
     * return the specified book, 
     * displaying an error message if the library does
     * not have the book with the specified call number, or if the book is not
     * checked out.
     * 
     * @param callNum the call number of the book to check out
     */
    public void returnBook(String callNum) {
        if ( getBook(callNum) == null) {
            System.out.println("there is no this book");
        }
        else {
            //  for (int index = 0; index < books.size(); index++) {
            //   if (books.get(index).getCallNumber().equals(callNum)) {
            //    books.get(index).returnBook();
            //                 }
            books.get(callNum).returnBook();
        }   
    }

    /**
     * display information about the library
     */
    //     public void print() {        
    //         System.out.println(libName);
    // 
    //         for (Book current : books) {
    //             System.out.println();
    //             current.print();
    //         }
    //     }
    public void print() {
        Set<String> keys = books.keySet();
        System.out.println(libName);
        for (String callNumber : keys) {
            books.get(callNumber).print();
        }
    }

    //     /**
    //      * get the position of a book by a particular author
    //      * if no author is found
    //      * return -1
    //      * @return i of the book
    //      * @param authorName the name of the author
    //      */
    //     private int getPositionOfBook(String authorName) {
    //         int i = -1;
    //         for (int index = 0; index < books.size(); index++) {
    //             if (books.get(index).getAuthor().equals(authorName)) {
    //                 i = index;
    //             }
    //         }
    //         return i;
    //     }
    /**
     * get the call number by a particular author
     * @param authorName the name of the author
     * @return callNumber by authorName
     */
    public String getSpecificCallNum(String authorName) {
        String call = "not found";
        Set<String> keys = books.keySet();
        for (String callNumber: keys) {
            Book abook = books.get(callNumber);
            if (authorName.equals(abook.getAuthor())) {
                call = abook.getCallNumber();
            }
        }
        return call;
    }

    /**
     * count the number of the books by subject
     */
    public void countBooksBySubject() {
        int math = 0;
        int computerScience = 0;
        int history = 0;
        int flyFishing = 0;
        Set<String> keys = books.keySet();
        //         for (int index = 0; index < books.size(); index++) 
        for (String callNumber : keys) {
            String subject = books.get(callNumber).getSubject();
            if (subject.equals("Math")) {
                math++;
            }
            else if (subject.equals("Computer Science")) {
                computerScience++;
            }
            else if (subject.equals("History")) {
                history++;
            }
            else {
                flyFishing++;
            }
        }
        System.out.println("Math:" + math);
        System.out.println("History:" + history);
        System.out.println("Computer Science:" + computerScience);
        System.out.println("Fly Fishing:" + flyFishing);
    }
}
