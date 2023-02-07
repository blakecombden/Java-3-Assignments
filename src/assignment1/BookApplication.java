package assignment1;

import java.util.Scanner;

/**
 * Runs and maintains the books and authors while the application is running.
 *
 * @author blake
 */
public class BookApplication {
    public static void main(String[] args) {

        System.out.print("Welcome to the books database application!\n");

        // load library
        Library library = BookDatabaseManager.loadLibrary();

        // ask user for input and use char to store menu choice
        Scanner input = new Scanner(System.in);
        char choice;

        do{
            // print options
            printMenu();

            // assign user input to choice variable
            choice = Character.toUpperCase(input.next().charAt(0));

            if (choice == 'A'){ // show all books

                System.out.println("\nBooks");
                library.getBookList().forEach(book -> Book.printBook(System.out, book));
                System.out.print("\n");

            } else if (choice == 'B'){ // show all authors

                System.out.println("\nAuthors");
                library.getAuthorList().forEach(author -> Author.printAuthor(System.out, author));
                System.out.print("\n");

            } else if (choice == 'C'){ // add new book

                // pass library to createBook method
                CreateBook.createBook(library);

            } else if (choice == 'D'){ // add new author

                // pass library to createAuthor method
                CreateAuthor.createAuthor(library);

            }

        } while (choice != 'X');

        System.out.println("\nLater!");
    }
    public static void printMenu(){
        System.out.println("\nPlease select an option:");
        System.out.println("(A) Show all books (with their authors)");
        System.out.println("(B) Show all authors (with their books)");
        System.out.println("(C) Add new book for existing author");
        System.out.println("(D) Add new author");
        System.out.println("(X) Exit");
    }
}