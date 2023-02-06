package assignment1;

public class LibraryTester {
    public static void main(String[] args) {
        Library library = BookDatabaseManager.loadLibrary();

        System.out.println("Books");
        library.getBookList().forEach(book -> Book.printBook(System.out, book));

        System.out.println("Authors");
        library.getAuthorList().forEach(author -> System.out.printf("\n%d, %s, %s", author.getAuthorID(), author.getFirstName(),
                author.getLastName()));

        //library.getAuthorList().get(0).setFirstName("SmellyPants");


    }
}
