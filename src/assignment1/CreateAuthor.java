package assignment1;

import java.util.List;
import java.util.Scanner;

public class CreateAuthor {
    public static void createAuthor(Library library) {

        Scanner input = new Scanner(System.in);

        // ask user for new author's first and last name
        System.out.println("Enter author's first name:");
        String firstName = input.next();

        System.out.println("Enter author's last name:");
        String lastName = input.next();

        // create authorList variable
        List<Author> authorList = library.getAuthorList();

        // id not needed for db update, but needed to create object
        int authorID = authorList.get(authorList.size() - 1).getAuthorID();

        // instantiate new author
        Author author = new Author(authorID + 1, firstName, lastName);

        // add to library's author list
        library.getAuthorList().add(author);

        // update database
        BookDatabaseManager.saveAuthor(author);
    }
}
