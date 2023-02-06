package assignment1;

import java.util.Scanner;

/**
 * Runs and maintains the books and authors while the application is running.
 *
 * @author blake
 */
public class BookApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to the books database application.");
        //TODO load library using BookDatabaseManager and save lists here
        Library library = BookDatabaseManager.loadLibrary();
        //TODO Do stuff and modify those lists
        //TODO Save library and pass those lists back to BookDatabaseManager to be saved
        Scanner input = new Scanner(System.in);
        char choice; // char to store menu choice

        do{
            printMenu();
            // assign user input to choice variable
            choice = Character.toUpperCase(input.next().charAt(0));

            if (choice == 'A'){
                //TODO load books list
                library.getBookList();
            } else if (choice == 'B'){
                //TODO load authors list
                library.getAuthorList();
            } else if (choice == 'C'){
                //TODO add book to database
                System.out.println("You chose C");
            } else if (choice == 'D'){
                //TODO add author to database
                System.out.println("You chose D");
            }

        } while (choice != 'X');

        System.out.println("\nLater!");
    }

    public static void printMenu(){
        System.out.println("\n\nPlease select an option:");
        System.out.println("(A) Show all books (with their authors)");
        System.out.println("(B) Show all authors (with their books)");
        System.out.println("(C) Add new book for existing author");
        System.out.println("(D) Add new author");
        System.out.println("(X) Exit");
    }
}
