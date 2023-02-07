package assignment1;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains attributes, constructor, getters, and setters for book objects
 * taken from the "titles" table in the "books" database.
 *
 * @author blake
 */
public class Book {

    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright;
    private List<Author> authorList;

    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        authorList = new LinkedList<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) { this.editionNumber = editionNumber; }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Author> getAuthorList() { return authorList; }

    public void addAuthor(Author author){ this.authorList.add(author); }

    public static void printBook(PrintStream printStream, Book book){
        printStream.printf("\n%s, Edition %d (%s) ISBN: %S", book.getTitle(), book.getEditionNumber(),
                book.getCopyright(), book.getIsbn());
        book.authorList.forEach(author -> System.out.printf("\n\t%s, %s", author.getLastName(), author.getFirstName()));
    }
}