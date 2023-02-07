package assignment1;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains attributes, constructor, getters, and setters for author objects
 * taken from the "authors" table in the "books" database.
 *
 * @author blake
 */
public class Author {

    private int authorID;
    private String firstName;
    private String lastName;
    private List<Book> bookList;

    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        bookList = new LinkedList<>();
    }

    public int getAuthorID() { return authorID; }

    public void setAuthorID(int authorID) { this.authorID = authorID; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<Book> getBookList() { return bookList; }

    public void addBook(Book book) { this.bookList.add(book); }

    public static void printAuthor(PrintStream printStream, Author author) {
        printStream.printf("\n%s %s (ID %d)", author.getFirstName(),
                author.getLastName(), author.getAuthorID());
        author.bookList.forEach(book -> System.out.printf("\n\t%s, Edition: %d (%S) ISBN: %s", book.getTitle(),
                book.getEditionNumber(), book.getCopyright(), book.getIsbn()));
    }
}