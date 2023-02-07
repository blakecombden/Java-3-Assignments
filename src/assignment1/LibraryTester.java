package assignment1;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryTester {
    public static void main(String[] args) {

        Library library = BookDatabaseManager.loadLibrary();

        System.out.println("Books");
        library.getBookList().forEach(book -> Book.printBook(System.out, book));

        System.out.println("Authors");
        List<Author> authorList = library.getAuthorList();
        authorList.forEach(author -> Author.printAuthor(System.out, author));

        System.out.println("\n");
        int authorID = authorList.get(authorList.size() - 1).getAuthorID();
        System.out.println(authorID);

        library.getAuthorList().get(0).setFirstName("SmellyPants");
    }
}
