package assignment1;

import java.util.Scanner;

public class CreateBook {

    public static void createBook(Library library) {

        Scanner input = new Scanner(System.in);

        // ask user for author's ID to reference author object
        System.out.println("Enter the author's ID:");
        int authorID = input.nextInt();
        Author author = library.getAuthorList().stream().filter(a -> a.getAuthorID() == authorID).findAny().get();

        // ask user for ISBN, title, edition, and copyright year of new book
        System.out.println("Enter ISBN:");
        String isbn = input.next();

        System.out.println("Enter title:");
        String title = input.next();

        System.out.println("Enter edition:");
        int editionNumber = input.nextInt();

        System.out.println("Enter copyright year:");
        String copyright = input.next();

        // create new book object
        Book book = new Book(isbn, title, editionNumber, copyright);

        // build relationship between new book object and existing author object
        author.addBook(book);
        book.addAuthor(author);

        // add book to library's book list
        library.getBookList().add(book);

        // update database
        BookDatabaseManager.saveBook(book, author);
    }
}
